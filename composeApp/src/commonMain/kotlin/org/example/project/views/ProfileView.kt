package org.example.project.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.example.project.apiClient.Client.createRating
import org.example.project.components.Avatar
import org.example.project.components.PostCard
import org.example.project.components.VoteState
import org.example.project.components.escapeWhitespace
import org.example.project.generated.Res
import org.example.project.generated.account_logout
import org.example.project.generated.city_munich_logo
import org.example.project.generated.logout_24px
import org.example.project.generated.indPost
import org.example.project.generated.martinsried
import org.example.project.generated.olympia
import org.example.project.model.ExtendedPost
import org.example.project.model.Tag
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Composable
fun ProfileView(navController: NavHostController) {
    val imageMap = mapOf(0 to painterResource(Res.drawable.martinsried), 1 to painterResource(Res.drawable.olympia))

    //val myData: List<ExtendedPost> = runBlocking {Client.getPosts()}
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
    var targetUsername = "Rico Finkbeiner"
    val userPosts = myData.filter { it.creatorUsername == targetUsername }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Avatar(
                modifier = Modifier.size(150.dp),
                avatar = painterResource(Res.drawable.city_munich_logo)
            )

            Text(
                text = "Max Mustermann",
                fontSize = 20.sp,                    // larger text
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.8f) // optional width constraint
            )

            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Res.drawable.logout_24px),
                        contentDescription = "Logout",
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(stringResource(Res.string.account_logout))
                }
            }
            Text(
                text = stringResource(Res.string.indPost),
                fontSize = 20.sp,                    // larger text
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.8f) // optional width constraint
            )

            LazyColumn {
                items(userPosts) { item ->
                    val voteState = remember { mutableStateOf(item.ownRating) }
                    val votesCount = remember { mutableStateOf(item.upvoteCount - item.downvoteCount) }

                    fun setRating(ratingVal: Int) {
                        val oldVal = voteState.value ?: 0
                        MainScope().launch { createRating(item.id, ratingVal) }
                        voteState.value = ratingVal
                        votesCount.value += ratingVal - oldVal
                    }

                    val modifier = Modifier
                    PostCard(
                        title = item.title,
                        description = item.description,
                        username = item.creatorUsername,
                        mainImage = imageMap.get(item.id),
                        isOfficial = true,
                        voteState = when (voteState.value) {
                            1 -> VoteState.UP
                            0 -> VoteState.NONE
                            -1 -> VoteState.DOWN
                            else -> VoteState.NONE
                        },
                        avatar = painterResource(Res.drawable.city_munich_logo),
                        createdAt = item.createdAt.toInstant(TimeZone.currentSystemDefault()),
                        modifier = modifier.padding(bottom = 9.dp),
                        votesCount = votesCount.value,
                        onUpClick = { setRating(1) },
                        onDownClick = { setRating(-1) },
                        onCardClick = {
                            val tags = item.tags.map { it.name }
                                .fold("", { acc, str -> if (acc.isEmpty()) str else "${acc},${str}" })
                            navController.navigate(
                                "postDetails/${escapeWhitespace(item.title)}/${escapeWhitespace(item.description)}/${tags}/${item.upvoteCount}/${item.downvoteCount}/${
                                    item.createdAt.toInstant(
                                        TimeZone.currentSystemDefault()
                                    ).toEpochMilliseconds()
                                }/${escapeWhitespace(item.creatorUsername)}"
                            )
                        },
                    )
                }

            }
        }
    }
}
