package org.example.project.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.example.project.navigation.BottomNavItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavigation(navController: NavHostController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            AddItem(item, currentRoute, navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    currentRoute: String?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = { Text(text = stringResource(screen.title)) },
        icon = {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = stringResource(screen.title)
            )
        },
        selected = currentRoute == screen.route,
        alwaysShowLabel = true,
        onClick = {
            if (currentRoute != screen.route) {
                navController.navigate(screen.route) {
                    // Avoid multiple copies on the back stack
                    launchSingleTop = true
                    // Optional: pop up to start destination to keep single instance
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                }
            }
        },
        colors = NavigationBarItemDefaults.colors()
    )
}