package org.example.project.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.example.project.components.PostList
import org.example.project.components.TagSelector
import org.example.project.model.ExtendedPost
import org.example.project.model.Tag
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun FeedView(navController: NavHostController) {
    val selectedTags: SnapshotStateList<Tag> = remember { mutableStateListOf() }
    val postFilter: (ExtendedPost) -> Boolean = { post ->
        println(selectedTags)
        selectedTags.isEmpty() || post.tags.any { it in selectedTags }
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TagSelector(selectedTags = selectedTags)
        PostList(modifier = Modifier, navController = navController, filter = postFilter)
    }
}
