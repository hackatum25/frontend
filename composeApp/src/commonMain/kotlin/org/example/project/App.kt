package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


import org.example.project.theme.MobileAppTheme
import org.jetbrains.compose.resources.stringResource

import org.jetbrains.compose.resources.stringResource
import org.example.project.generated.Res
import org.example.project.generated.city_munich_logo
import org.example.project.generated.compose_multiplatform
import org.example.project.generated.hello
import org.example.project.generated.martinsried
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Composable
@Preview
fun App() {
        MobileAppTheme {
            var showContent by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Post(
                    title = "U6 Extension to Martinsried",
                    mainImage = painterResource(Res.drawable.martinsried),
                    isOfficial = true,
                    voteState = VoteState.UP,
                    avatar = painterResource(Res.drawable.city_munich_logo),
                    createdAt = Clock.System.now().minus(4.days),
                    votesCount = 67,
                    onUpClick = { /*...*/ },
                    onDownClick = { /*...*/ },
                    onCardClick = { /*...*/ }
                )
                Button(onClick = { showContent = !showContent }) {
                    Text("Click you!")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        //Text("Compose: $greeting", color = MaterialTheme.colorScheme.onBackground)
                        Text(
                            stringResource(Res.string.hello), color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }
        }
    
}