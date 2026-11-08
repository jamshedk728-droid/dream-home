package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LuxuryDarkColorScheme = darkColorScheme(
    primary = MatteGold,
    onPrimary = DarkCharcoal,
    primaryContainer = DeepWalnut,
    onPrimaryContainer = PuttyBeige,
    secondary = PuttyBeige,
    onSecondary = DarkCharcoal,
    tertiary = MatteGoldBright,
    onTertiary = DarkCharcoal,
    background = DarkCharcoal,
    onBackground = IvoryWhite,
    surface = DarkCharcoalSurface,
    onSurface = IvoryWhite,
    surfaceVariant = WalnutBrown,
    onSurfaceVariant = PuttyBeige,
    outline = MatteGold
)

private val LuxuryLightColorScheme = lightColorScheme(
    primary = WalnutBrown,
    onPrimary = IvoryWhite,
    primaryContainer = PuttyBeige,
    onPrimaryContainer = DarkCharcoal,
    secondary = MatteGold,
    onSecondary = DarkCharcoal,
    tertiary = MatteGoldBright,
    onTertiary = DarkCharcoal,
    background = IvoryWhite,
    onBackground = DarkCharcoal,
    surface = Color.White,
    onSurface = DarkCharcoal,
    surfaceVariant = SoftStoneGrey,
    onSurfaceVariant = DarkCharcoal,
    outline = PuttyBeige
)

@Composable
fun JnkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) LuxuryDarkColorScheme else LuxuryLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = JnkTypography,
        content = content
    )
}
