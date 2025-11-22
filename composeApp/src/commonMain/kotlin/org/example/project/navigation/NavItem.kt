package org.example.project.navigation

import org.example.project.generated.Res
import org.example.project.generated.navbar_create_post
import org.example.project.generated.navbar_feed
import org.example.project.generated.navbar_post_details
import org.example.project.generated.navbar_profile
import org.example.project.generated.news_24px
import org.example.project.generated.person_24px
import org.example.project.generated.send_24px
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class NavItem(
    val route: String,
    var title: StringResource,
    var icon: DrawableResource
) {
    object Home : NavItem("feed", Res.string.navbar_feed, Res.drawable.news_24px)
    object Profile : NavItem("profile", Res.string.navbar_profile, Res.drawable.person_24px)
    object CreatePost : NavItem("createPost", Res.string.navbar_create_post, Res.drawable.send_24px)
    object Details : NavItem("postDetails", Res.string.navbar_post_details, Res.drawable.send_24px)

    companion object {
        private val map = listOf(Home, Profile, CreatePost, Details).associateBy { it.route }
        fun fromRouteWithArgs(route: String): NavItem? {
            val base = route.substringBefore('/') // splits "profile/123" -> "profile"
            return map[base]
        }
    }
}