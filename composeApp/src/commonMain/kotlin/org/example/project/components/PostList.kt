package org.example.project.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.example.project.apiClient.Client
import org.example.project.apiClient.Client.createRating
import org.example.project.generated.Res
import org.example.project.generated.city_munich_logo
import org.example.project.generated.martinsried
import org.example.project.generated.olympia
import org.example.project.model.ExtendedPost
import org.example.project.model.Tag
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun escapeWhitespace(s: String): String {
    return s.replace(" ", "%20")
}

@OptIn(ExperimentalTime::class)
@Composable
fun PostList(modifier: Modifier, navController: NavHostController, filter: (ExtendedPost) -> Boolean) {


    val imageMap = mapOf(0 to painterResource(Res.drawable.martinsried), 1 to painterResource(Res.drawable.olympia))
    val myData: List<ExtendedPost> = runBlocking {Client.getPosts()}
    /*
    val myData: List<ExtendedPost> = listOf(ExtendedPost(0, "Erweiterung der U6 nach Martinsried", "Der U-Bahnhof Martinsried ist ein in Bau befindlicher U-Bahnhof auf dem Gemeindegebiet von Planegg", Clock.System.now().toLocalDateTime(
        TimeZone.currentSystemDefault())
        , 0, 420, -1, "Stadt München", true, listOf(Tag.OFFICIAL, Tag.TRANSPORT)
    ),
        ExtendedPost(1, "Olympia Bürgerentscheid", "Der Freistaat Bayern unterstützt die Bewerbung Münchens für die Olympischen und Paralympischen Sommerspiele ab 2036", Clock.System.now().toLocalDateTime(
            TimeZone.currentSystemDefault())
            , 0, 420, -1, "Freistaat Bayern", true, listOf(Tag.OFFICIAL, Tag.EVENTS)
        ),
                ExtendedPost(2, "Weniger Hausaufgaben", "LETS GOOOOO", Clock.System.now().toLocalDateTime(
            TimeZone.currentSystemDefault())
        , 0, 420, -1, "Rico Finkbeiner", false, listOf() )
        )
     */
    val filtered = myData.filter { filter(it) }

    LazyColumn {
        items(filtered) { item ->
            val voteState = remember { mutableStateOf(item.ownRating) }
            val votesCount = remember { mutableStateOf(item.upvoteCount - item.downvoteCount) }

            fun setRating(ratingVal: Int) {
                val oldVal = voteState.value ?: 0
                MainScope().launch { createRating(item.id, ratingVal) }
                voteState.value = ratingVal
                votesCount.value += ratingVal - oldVal
            }

            PostCard(
                title = item.title,
                description = item.description,
                username = item.creatorUsername,
                mainImage = imageMap.get(item.id),
                isOfficial = item.verified,
                voteState = when (voteState.value) {
                    1  -> VoteState.UP
                    0  -> VoteState.NONE
                    -1 -> VoteState.DOWN
                    else -> VoteState.NONE},
                avatar = painterResource(Res.drawable.city_munich_logo),
                createdAt = item.createdAt.toInstant(TimeZone.currentSystemDefault()),
                modifier = modifier.padding(bottom = 9.dp),
                votesCount = votesCount.value,
                onUpClick = { setRating(1) },
                onDownClick = { setRating( -1) },
                onCardClick = {
                    val tags = item.tags.map { it.name }.fold("", { acc, str -> if (acc.isEmpty()) str else "${acc},${str}" })
                    navController.navigate("postDetails/${escapeWhitespace(item.title)}/${escapeWhitespace(item.description)}/${tags}/${item.upvoteCount}/${item.downvoteCount}/${item.createdAt.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()}/${escapeWhitespace(item.creatorUsername)}") },
            )
        }
    }
}
