package org.example.project.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.example.project.components.Avatar
import org.example.project.components.TagFilter
import org.example.project.generated.Res
import org.example.project.generated.account_logout
import org.example.project.generated.city_munich_logo
import org.example.project.generated.logout_24px
import org.example.project.generated.post_publish
import org.example.project.generated.send_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CreatePostView(navController: NavHostController,isNavbarVisible: MutableState<Boolean>){
    DisposableEffect(Unit) {
        onDispose {
            isNavbarVisible.value = true
        }
    }

    Column{
        Row(verticalAlignment = Alignment.CenterVertically){
            Avatar(
                avatar = painterResource(Res.drawable.city_munich_logo)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("Landeshauptstadt München")
            Button(
                onClick = {
                    navController.navigate("feed")
                },
            ) {
                    Icon(
                        painter = painterResource(Res.drawable.send_24px),
                        contentDescription = "Logout",
                    )
            }
        }
        OutlinedTextField(
            state = rememberTextFieldState(""),
            lineLimits = TextFieldLineLimits.MultiLine(),
            placeholder = { Text("") },
            label = { Text("Enter Title") },
            modifier = Modifier.padding(20.dp)
        )
        TagFilter()
        OutlinedTextField(
            state = rememberTextFieldState(""),
            lineLimits = TextFieldLineLimits.MultiLine(),
            placeholder = { Text("") },
            label = { Text("Enter Description") },
            modifier = Modifier.padding(20.dp)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
    }
}
