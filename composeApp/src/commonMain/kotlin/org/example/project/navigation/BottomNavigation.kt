package org.example.project.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
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
            AddItem(item, navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
  screen: BottomNavItem,
  navController: NavHostController
) {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  val selected = currentDestination?.hierarchy?.any { it.route == screen.route || it.route?.startsWith(screen.route) == true } == true

  NavigationBarItem(
    label = { Text(stringResource(screen.title)) },
    icon = { Icon(painterResource(screen.icon), contentDescription = null) },
    selected = selected,
    onClick = {
      if (!selected) {
        navController.navigate(screen.route) {
          launchSingleTop = true
          restoreState = true
        }
      }
    }
  )
}
