package org.example.project.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.runBlocking
import org.example.project.apiClient.Client
import org.example.project.components.Avatar
import org.example.project.components.TagSelector
import org.example.project.generated.Res
import org.example.project.generated.city_munich_logo
import org.example.project.generated.logout_24px
import org.example.project.generated.post_cancel
import org.example.project.generated.post_create_new
import org.example.project.generated.post_enter_description
import org.example.project.generated.post_enter_title
import org.example.project.generated.post_publish
import org.example.project.generated.post_select_tags
import org.example.project.model.Post
import org.example.project.model.Tag
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CreatePostView(
    navController: NavHostController,
    isNavbarVisible: MutableState<Boolean>
) {
    val title = remember { mutableStateOf("") }
    val description = remember {mutableStateOf("")}
    val selectedTags: SnapshotStateList<Tag> = remember { mutableStateListOf() }

    DisposableEffect(Unit) {
        onDispose { isNavbarVisible.value = true }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Top app row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Avatar(
                    avatar = painterResource(Res.drawable.city_munich_logo),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Landeshauptstadt München",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(Res.string.post_create_new),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Card with form content
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Title input
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { newTitle: String ->  title.value = newTitle },
                    placeholder = { Text("") },
                    label = { Text(text = stringResource(Res.string.post_enter_title)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(Res.string.post_select_tags),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Tag filter area with card background
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        TagSelector(selectedTags = selectedTags)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Description input
                OutlinedTextField(
                    value = description.value,
                    onValueChange = { newDesc: String ->  description.value = newDesc },
                    placeholder = { Text("") },
                    label = { Text(stringResource(Res.string.post_enter_description)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 120.dp)
                )

                Spacer(modifier = Modifier.weight(1f))


                // Actions row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            navController.navigate("feed") },
                    ) {
                        Text(stringResource(Res.string.post_cancel))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            runBlocking {Client.post(Post(title.value, description.value, selectedTags.toList()))}
                            navController.navigate("feed") },
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(Res.drawable.logout_24px),
                                contentDescription = "Publish",
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(stringResource(Res.string.post_publish))
                        }
                    }
                }
            }
        }
    }
}
