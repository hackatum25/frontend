package org.example.project.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    tertiary = Tertiary,
    error = Error,
    surface = Surface,
    onSurface = OnSurface,
    background = Background,
    onBackground = OnBackground,
    primaryContainer = PrimaryContainer,
)

val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark,
    error = ErrorDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    primaryContainer = PrimaryContainerDark
)

@Composable
fun MobileAppTheme(
    content: @Composable () -> Unit
) {
   val theme = if(isSystemInDarkTheme()) {
       DarkColorScheme
   } else {
       LightColorScheme
   }
    MaterialTheme(
        colorScheme = theme,
        content = content
    )
}