package org.example.project.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.runBlocking
import org.example.project.apiClient.Client
import org.example.project.generated.Res
import org.example.project.generated.account_login
import org.example.project.generated.account_password
import org.example.project.generated.account_username
import org.example.project.generated.logging
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun LoginScreen(
    navController: NavHostController = rememberNavController()
    , isNavbarVisible: MutableState<Boolean>
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.logging),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = username.value,
            onValueChange = { newUsername ->
                username.value = newUsername
            },
            label = { Text(stringResource(Res.string.account_username)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = password.value,
            onValueChange = {newPassword ->
                password.value = newPassword
            },
            label = { Text(stringResource(Res.string.account_password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                runBlocking {Client.login(username.value, password.value)}
                isNavbarVisible.value = true
                navController.navigate("feed")
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(Res.string.account_login))
        }
    }
}
