package org.example.project

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.project.generated.Res
import org.example.project.generated.add_24px
import org.jetbrains.compose.resources.painterResource

@Composable
fun ActionButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(painter = painterResource( Res.drawable.add_24px), "Small floating action button.")
    }
}