package org.example.project.navigation

import org.example.project.generated.Res
import org.example.project.generated.navbar_feed
import org.example.project.generated.navbar_profile
import org.example.project.generated.news_24px
import org.example.project.generated.person_24px
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class BottomNavItem(
    val route: String,
    var title: StringResource,
    var icon: DrawableResource
) {
    object Home : BottomNavItem("feed", Res.string.navbar_feed, Res.drawable.news_24px)
    object Profile : BottomNavItem("profile", Res.string.navbar_profile, Res.drawable.person_24px)
}