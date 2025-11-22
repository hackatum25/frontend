package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.PostDetailsView
import org.example.project.views.*
import org.example.project.views.FeedView
import org.example.project.views.ProfileView
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "feed") {
        composable("feed") {
            FeedView(navController)
        }
        composable("profile") {
            ProfileView(navController)
        }
        /*
        composable(
            "posts/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.StringType })
        ) { backStackEntry ->
            PostDetailView("Unknown")
        }
         */
        composable("postDetails") {
            PostDetailsView(
                title = "U6 Extension to Martinsried",
                description = "The U6 subway line will be extended to Martinsried, improving transportation for students and researchers in the area. This project is expected to be completed by 2025 and will significantly reduce commute times for thousands of daily commuters.",
                labels = listOf("Transport", "Infrastructure", "Public Works", "Urban Development"),
                upVote = 67,
                downVote = 12,
                createDate = Clock.System.now().minus(4.days),
                navController = navController
            )
        }
    }
}
