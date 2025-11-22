package org.example.project.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.example.project.apiClient.Client
import org.example.project.generated.Res
import org.example.project.generated.city_munich_logo
import org.example.project.generated.martinsried
import org.example.project.model.ExtendedPost
import org.example.project.model.Post
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun PostList(modifier: Modifier, navController: NavHostController, client: Client){
    val myData: List<ExtendedPost> = runBlocking {client.getPosts()}
    // val myData: List<ExtendedPost> = listOf(ExtendedPost("Erweiterung der U6 nach Martinsried und noch sdfasdfasdfa asdfsa sadf", "Heyheyhey", Clock.System.now().toLocalDateTime(
    //    TimeZone.currentSystemDefault())
    //    , 0, 420, -1 ))
    LazyColumn {
        items(myData) { item ->
            PostCard(
                title = item.title,
                description = item.description,
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
                onUpClick = { /*...*/ },
                onDownClick = { /*...*/ },
                onCardClick = { navController.navigate("postDetails") },
            )
        }
    }
}
