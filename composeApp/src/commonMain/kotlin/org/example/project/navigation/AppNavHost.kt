package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.project.views.FeedView
import org.example.project.views.PostDetailView
import org.example.project.views.ProfileView

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "feed") {
        composable("feed") {
            FeedView(navController)
        }
        composable("profile") {
            ProfileView(navController)
        }
        composable(
            "posts/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.StringType })
        ) { backStackEntry ->
            PostDetailView("Unknown")
        }
    }
}
