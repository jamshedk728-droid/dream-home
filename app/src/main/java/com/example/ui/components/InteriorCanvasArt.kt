package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.ui.theme.DarkCharcoal
import com.example.ui.theme.IvoryWhite
import com.example.ui.theme.MatteGold
import com.example.ui.theme.PuttyBeige
import com.example.ui.theme.WalnutBrown

@Composable
fun HeroInteriorCanvasBanner(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "coveGlow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(DarkCharcoal, Color(0xFF1F1813), DarkCharcoal)
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // 1. Perspective Wall Background Lines
            drawLine(
                color = Color(0x33C7A248),
                start = Offset(0f, 0f),
                end = Offset(w * 0.2f, h * 0.35f),
                strokeWidth = 2f
            )
            drawLine(
                color = Color(0x33C7A248),
                start = Offset(w, 0f),
                end = Offset(w * 0.8f, h * 0.35f),
                strokeWidth = 2f
            )

            // 2. Main POP Ceiling Step Frame
            val ceilingPath = Path().apply {
                moveTo(w * 0.1f, h * 0.05f)
                lineTo(w * 0.9f, h * 0.05f)
                lineTo(w * 0.82f, h * 0.32f)
                lineTo(w * 0.18f, h * 0.32f)
                close()
            }
            drawPath(
                path = ceilingPath,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF3B2B20), Color(0xFF231A14))
                )
            )
            drawPath(
                path = ceilingPath,
                color = Color(0xFFC7A248),
                style = Stroke(width = 3f)
            )

            // Inner LED Cove Light Glow
            val innerCove = Path().apply {
                moveTo(w * 0.22f, h * 0.12f)
                lineTo(w * 0.78f, h * 0.12f)
                lineTo(w * 0.72f, h * 0.26f)
                lineTo(w * 0.28f, h * 0.26f)
                close()
            }
            drawPath(
                path = innerCove,
                color = Color(0xFFFFD700).copy(alpha = glowAlpha * 0.35f)
            )
            drawPath(
                path = innerCove,
                color = Color(0xFFFFE599).copy(alpha = glowAlpha),
                style = Stroke(width = 4f)
            )

            // Center Chandelier Light Lines
            drawLine(
                color = Color(0xFFFFD700),
                start = Offset(w * 0.5f, h * 0.19f),
                end = Offset(w * 0.5f, h * 0.38f),
                strokeWidth = 3f
            )
            drawCircle(
                color = Color(0xFFFFE599),
                radius = 16f,
                center = Offset(w * 0.5f, h * 0.38f)
            )
            drawCircle(
                color = Color(0xFFFFD700).copy(alpha = glowAlpha),
                radius = 28f,
                center = Offset(w * 0.5f, h * 0.38f)
            )

            // 3. Wall Paneling (Walnut Wood Louvers on Right Wall)
            val panelLeft = w * 0.72f
            for (i in 0..6) {
                val x = panelLeft + i * (w * 0.035f)
                drawRect(
                    color = Color(0xFF6E4B3A),
                    topLeft = Offset(x, h * 0.35f),
                    size = Size(w * 0.02f, h * 0.55f)
                )
                drawRect(
                    color = Color(0xFFC7A248).copy(alpha = 0.6f),
                    topLeft = Offset(x + w * 0.015f, h * 0.35f),
                    size = Size(2f, h * 0.55f)
                )
            }

            // 4. Designer L-Sofa Silhouette
            val sofaPath = Path().apply {
                moveTo(w * 0.12f, h * 0.78f)
                lineTo(w * 0.55f, h * 0.78f)
                lineTo(w * 0.55f, h * 0.72f)
                lineTo(w * 0.62f, h * 0.72f)
                lineTo(w * 0.62f, h * 0.90f)
                lineTo(w * 0.12f, h * 0.90f)
                close()
            }
            drawPath(
                path = sofaPath,
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF8B5E3C), Color(0xFF5A3E2B))
                )
            )
            drawPath(
                path = sofaPath,
                color = Color(0xFFD8C8B8),
                style = Stroke(width = 2f)
            )

            // 5. Floor Warm Reflection Gradient
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color(0x22C7A248), Color(0x442E2E2E))
                ),
                topLeft = Offset(0f, h * 0.88f),
                size = Size(w, h * 0.12f)
            )
        }
    }
}

@Composable
fun CeilingGraphicIcon(
    modifier: Modifier = Modifier,
    primaryColor: Color = MatteGold
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        
        // Outer ceiling frame
        drawRect(
            color = primaryColor,
            topLeft = Offset(w * 0.1f, h * 0.1f),
            size = Size(w * 0.8f, h * 0.8f),
            style = Stroke(width = 4f)
        )
        // Inner step
        drawRect(
            color = primaryColor.copy(alpha = 0.7f),
            topLeft = Offset(w * 0.25f, h * 0.25f),
            size = Size(w * 0.5f, h * 0.5f),
            style = Stroke(width = 3f)
        )
        // Center LED spotlight bulb
        drawCircle(
            color = primaryColor,
            radius = w * 0.1f,
            center = Offset(w * 0.5f, h * 0.5f)
        )
    }
}
