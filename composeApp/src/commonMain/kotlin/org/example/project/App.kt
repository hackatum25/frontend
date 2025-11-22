package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.example.project.apiClient.Client
import org.example.project.components.ActionButton
import org.example.project.components.VoteState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


import org.example.project.theme.MobileAppTheme

import org.example.project.generated.Res
import org.example.project.generated.city_munich_logo
import org.example.project.generated.martinsried
import org.example.project.navigation.AppNavHost
import org.example.project.navigation.BottomNavigation
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
@Preview
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val isNavbarVisible = remember { mutableStateOf(true) }

    MobileAppTheme {
        Scaffold(
            bottomBar = {
                if(isNavbarVisible.value) {
                BottomNavigation(navController)
                } },
            floatingActionButton = {
                ActionButton(onClick = {
                    isNavbarVisible.value=false
                    navController.navigate("createPost")})
            },
            floatingActionButtonPosition = FabPosition.End, // or Center
        ) {
                AppNavHost(navController, isNavbarVisible)
            }
    }
}