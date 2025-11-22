package org.example.project.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
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
import org.example.project.model.ExtendedPost
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun PostList(modifier: Modifier, navController: NavHostController){
    //val myData: List<ExtendedPost> = runBlocking {Client.getPosts()}
    val myData: List<ExtendedPost> = listOf(ExtendedPost(0, "Erweiterung der U6 nach Martinsried und noch sdfasdfasdfa asdfsa sadf", "Der U-Bahnhof Martinsried ist ein in Bau befindlicher U-Bahnhof auf dem Gemeindegebiet von Planegg", Clock.System.now().toLocalDateTime(
        TimeZone.currentSystemDefault())
        , 0, 420, -1 ))
    LazyColumn {
        items(myData) { item ->
            PostCard(
                title = item.title,
                description = item.description,
                username = "Landeshauptstadt München",
                mainImage = painterResource(Res.drawable.martinsried),
                isOfficial = true,
                voteState = when (item.ownRating) {
                    1  -> VoteState.UP
                    0  -> VoteState.NONE
                    -1 -> VoteState.DOWN
                    else -> VoteState.NONE},
                avatar = painterResource(Res.drawable.city_munich_logo),
                createdAt = item.createdAt.toInstant(TimeZone.currentSystemDefault()),
                modifier = modifier.padding(bottom = 9.dp),
                votesCount = item.upvoteCount-item.downvoteCount,
                onUpClick = { MainScope().launch(Dispatchers.Default) { createRating(item.id, 1) }},
                onDownClick = { MainScope().launch(Dispatchers.Default) { createRating(item.id, -1) } },
                onCardClick = { navController.navigate("postDetails") },
            )
        }
    }
}
