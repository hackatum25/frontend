package com.example.compose
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import org.example.project.theme.backgroundDark
import org.example.project.theme.backgroundLight
import org.example.project.theme.errorContainerDark
import org.example.project.theme.errorContainerLight
import org.example.project.theme.errorDark
import org.example.project.theme.errorLight
import org.example.project.theme.inverseOnSurfaceDark
import org.example.project.theme.inverseOnSurfaceLight
import org.example.project.theme.inversePrimaryDark
import org.example.project.theme.inversePrimaryLight
import org.example.project.theme.inverseSurfaceDark
import org.example.project.theme.inverseSurfaceLight
import org.example.project.theme.onBackgroundDark
import org.example.project.theme.onBackgroundLight
import org.example.project.theme.onErrorContainerDark
import org.example.project.theme.onErrorContainerLight
import org.example.project.theme.onErrorDark
import org.example.project.theme.onErrorLight
import org.example.project.theme.onPrimaryContainerDark
import org.example.project.theme.onPrimaryContainerLight
import org.example.project.theme.onPrimaryDark
import org.example.project.theme.onPrimaryLight
import org.example.project.theme.onSecondaryContainerDark
import org.example.project.theme.onSecondaryContainerLight
import org.example.project.theme.onSecondaryDark
import org.example.project.theme.onSecondaryLight
import org.example.project.theme.onSurfaceDark
import org.example.project.theme.onSurfaceLight
import org.example.project.theme.onSurfaceVariantDark
import org.example.project.theme.onSurfaceVariantLight
import org.example.project.theme.onTertiaryContainerDark
import org.example.project.theme.onTertiaryContainerLight
import org.example.project.theme.onTertiaryDark
import org.example.project.theme.onTertiaryLight
import org.example.project.theme.outlineDark
import org.example.project.theme.outlineLight
import org.example.project.theme.outlineVariantDark
import org.example.project.theme.outlineVariantLight
import org.example.project.theme.primaryContainerDark
import org.example.project.theme.primaryContainerLight
import org.example.project.theme.primaryDark
import org.example.project.theme.primaryLight
import org.example.project.theme.scrimDark
import org.example.project.theme.scrimLight
import org.example.project.theme.secondaryContainerDark
import org.example.project.theme.secondaryContainerLight
import org.example.project.theme.secondaryDark
import org.example.project.theme.secondaryLight
import org.example.project.theme.surfaceBrightDark
import org.example.project.theme.surfaceBrightLight
import org.example.project.theme.surfaceContainerDark
import org.example.project.theme.surfaceContainerHighDark
import org.example.project.theme.surfaceContainerHighLight
import org.example.project.theme.surfaceContainerHighestDark
import org.example.project.theme.surfaceContainerHighestLight
import org.example.project.theme.surfaceContainerLight
import org.example.project.theme.surfaceContainerLowDark
import org.example.project.theme.surfaceContainerLowLight
import org.example.project.theme.surfaceContainerLowestDark
import org.example.project.theme.surfaceContainerLowestLight
import org.example.project.theme.surfaceDark
import org.example.project.theme.surfaceDimDark
import org.example.project.theme.surfaceDimLight
import org.example.project.theme.surfaceLight
import org.example.project.theme.surfaceVariantDark
import org.example.project.theme.surfaceVariantLight
import org.example.project.theme.tertiaryContainerDark
import org.example.project.theme.tertiaryContainerLight
import org.example.project.theme.tertiaryDark
import org.example.project.theme.tertiaryLight

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun MobileAppTheme(
    content: @Composable () -> Unit
) {
    val theme = if(isSystemInDarkTheme()) {
        darkScheme
    } else {
        lightScheme
    }
    MaterialTheme(
        colorScheme = theme,
        content = content
    )
}