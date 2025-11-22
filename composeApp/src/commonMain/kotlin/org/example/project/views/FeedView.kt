package org.example.project.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.example.project.components.Post
import org.example.project.components.VoteState
import org.example.project.generated.Res
import org.example.project.generated.city_munich_logo
import org.example.project.generated.martinsried
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun FeedView(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Post(
            title = "U6 Extension to Martinsried",
            mainImage = painterResource(Res.drawable.martinsried),
            isOfficial = true,
            voteState = VoteState.UP,
            avatar = painterResource(Res.drawable.city_munich_logo),
            createdAt = Clock.System.now().minus(4.days),
            votesCount = 67,
            onUpClick = { /*...*/ },
            onDownClick = { /*...*/ },
            onCardClick = { /*...*/ }
        )
    }
}
