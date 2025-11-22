package org.example.project.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.example.project.generated.Res
import org.example.project.generated.city_munich_logo
import org.example.project.generated.martinsried
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun PostList(modifier: Modifier, navController: NavHostController) {
    val myData = listOf("Hello,", "world!", "fasdfa", "sada")
    LazyColumn {
        items(myData) { item ->
            Post(
                title = item,
                mainImage = painterResource(Res.drawable.martinsried),
                isOfficial = true,
                voteState = VoteState.UP,
                avatar = painterResource(Res.drawable.city_munich_logo),
                createdAt = Clock.System.now().minus(4.days),
                modifier = modifier.padding(bottom = 9.dp),
                votesCount = 67,
                onUpClick = { /*...*/ },
                onDownClick = { /*...*/ },
                onCardClick = { navController.navigate("postDetails/Hello%20World/This%20is%20a%20test%20post/kotlin,compose,multiplatform/42/3/1698134400000") },
            )
        }
    }
}