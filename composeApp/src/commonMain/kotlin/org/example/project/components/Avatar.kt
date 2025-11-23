package org.example.project.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.draw.clip
import org.example.project.generated.Res
import org.example.project.generated.jetbrains_logo
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun Avatar(modifier: Modifier = Modifier, avatar: Painter = painterResource(Res.drawable.jetbrains_logo)) {
    Image(
        painter = avatar,
        contentDescription = "Avatar",
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White)
            .border(2.dp, Color.White, CircleShape)
            .size(40.dp),           // default size
        contentScale = ContentScale.Crop
    )
}