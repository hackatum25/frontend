package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.savedstate.SavedState
import org.example.project.PostDetailsView
//import org.example.project.PostDetailsView
import org.example.project.views.*
import org.example.project.views.FeedView
import org.example.project.views.ProfileView
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder

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
        */

            composable(
                "postDetails/{title}/{description}/{labels}/{upVote}/{downVote}/{createDate}",
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType },
                    navArgument("labels") { type = NavType.StringType },
                    navArgument("upVote") { type = NavType.IntType },
                    navArgument("downVote") { type = NavType.IntType },
                    navArgument("createDate") { type = NavType.LongType }
                )
            ) { backStackEntry ->

                // Get the route string
                val route = backStackEntry.destination.route ?: ""

                val args = route.split('/')

                if (args.size == 7) {
                    val title = args[1]
                    val description = args[2]
                    val labels = args[3].split(",")           // split by comma
                        .map { it.trim() }                         // remove extra spaces
                        .filter { it.isNotEmpty() }
                    val upVote = args[4].toInt()
                    val downVote = args[5].toInt()
                    val createDateMillis = args.getOrNull(6)?.toLongOrNull() ?: 0L
                    val createDate = Instant.fromEpochMilliseconds(createDateMillis)

                    PostDetailsView(
                        title = title,
                        description = description,
                        labels = labels,
                        upVote = upVote,
                        downVote = downVote,
                        createDate = createDate,
                        navController = navController
                    )
                } else {
                    // Fallback if regex fails
                    PostDetailsView(
                        title = "",
                        description = "",
                        labels = emptyList(),
                        upVote = 0,
                        downVote = 0,
                        createDate = Clock.System.now(),
                        navController = navController
                    )
                }
        }
    }
}
