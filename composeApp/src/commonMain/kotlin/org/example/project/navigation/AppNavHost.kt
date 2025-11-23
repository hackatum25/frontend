package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.savedstate.read
import org.example.project.views.PostDetailsView
import org.example.project.views.*
import org.example.project.views.FeedView
import org.example.project.views.ProfileView
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), isNavbarVisible: MutableState<Boolean>) {
        NavHost(navController = navController, startDestination = "feed") {
        composable("feed") {
            FeedView(navController)
        }
        composable("profile") {
            ProfileView(navController)
        }
        composable("createPost") {
            CreatePostView(navController, isNavbarVisible)
        }
        composable("login"){
            LoginView(navController, isNavbarVisible)
        }
        /*
        composable(
            "posts/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.StringType })
        ) { backStackEntry ->
            PostDetailView("Unknown")
        }
         */
        composable(
            "postDetails/{title}/{description}/{labels}/{upVote}/{downVote}/{createDate}/{author}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("labels") { type = NavType.StringType },
                navArgument("upVote") { type = NavType.IntType },
                navArgument("downVote") { type = NavType.IntType },
                navArgument("createDate") { type = NavType.LongType },
                navArgument("author") { type = NavType.StringType },
            )
        ) { backStackEntry ->

            // Get the route string

            val args = backStackEntry.arguments

            if (true) {
                val title = args!!.read { getString("title") }
                val description = args!!.read { getString("description") }
                val labels = args!!.read { getString("labels") }.split(",")           // split by comma
                    .map { it.trim() }                         // remove extra spaces
                    .filter { it.isNotEmpty() }
                val upVote = args!!.read { getInt("upVote") }
                val downVote = args!!.read { getInt("downVote") }
                val createDateMillis = args!!.read { getLong("createDate") }
                val createDate = Instant.fromEpochMilliseconds(createDateMillis)
                val author = args!!.read { getString("author") }

                PostDetailsView(
                    title = title,
                    description = description,
                    labels = labels,
                    upVote = upVote,
                    downVote = downVote,
                    createDate = createDate,
                    author = author,
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
                    author = "",
                    navController = navController
                )
            }
        }
    }
}
