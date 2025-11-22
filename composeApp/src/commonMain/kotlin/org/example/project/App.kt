package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.MobileAppTheme
import org.example.project.apiClient.Client
import org.example.project.components.ActionButton
import org.example.project.components.VoteState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview



import org.example.project.generated.Res
import org.example.project.generated.city_munich_logo
import org.example.project.generated.martinsried
import org.example.project.navigation.AppNavHost
import org.example.project.navigation.BottomNavigation
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val isNavbarVisible = remember { mutableStateOf(true) }

    MobileAppTheme {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Your Feed")
                    }
                )
            },
            bottomBar = {
                if(isNavbarVisible.value) {
                    BottomNavigation(navController)
                } },
            floatingActionButton = {
                if(isNavbarVisible.value){
                ActionButton(onClick = {
                    isNavbarVisible.value = false
                    navController.navigate("createPost")
                })
            }
            },
            floatingActionButtonPosition = FabPosition.End, // or Center
        ) {
                paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // use the content padding here
            ) {
                AppNavHost(navController, isNavbarVisible)
            }
            }
    }
}
