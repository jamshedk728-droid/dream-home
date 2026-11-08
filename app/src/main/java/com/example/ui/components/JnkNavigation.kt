package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Grid3x3
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material.icons.outlined.Grid3x3
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.JnkCompanyInfo
import com.example.ui.theme.DarkCharcoal
import com.example.ui.theme.IvoryWhite
import com.example.ui.theme.MatteGold
import com.example.ui.theme.PuttyBeige
import com.example.ui.theme.WalnutBrown

enum class JnkScreen(val title: String, val route: String) {
    Home("Home", "home"),
    Portfolio("Portfolio & POP", "portfolio"),
    Estimator("Cost Estimator", "estimator"),
    Consultation("Book Consultation", "consultation"),
    AiDesigner("AI Designer", "ai_designer")
}

@Composable
fun JnkTopBar(
    onCallClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    onMapClick: () -> Unit,
    onInstagramClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = DarkCharcoal,
        tonalElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(DarkCharcoal, Color(0xFF1E1E1E))
                    )
                )
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(MatteGold, WalnutBrown)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "JNK",
                            color = IvoryWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Serif
                        )
                    }

                    Column {
                        Text(
                            text = JnkCompanyInfo.NAME,
                            color = IvoryWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Serif,
                            letterSpacing = 0.5.sp
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = MatteGold,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "Tuni, AP • Phone: +91 9613332999",
                                color = PuttyBeige,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onCallClick,
                        modifier = Modifier.size(36.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MatteGold.copy(alpha = 0.2f),
                            contentColor = MatteGold
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Call JNK",
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onWhatsAppClick,
                        modifier = Modifier.size(36.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color(0xFF25D366).copy(alpha = 0.2f),
                            contentColor = Color(0xFF25D366)
                        )
                    ) {
                        Text(
                            text = "WA",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = Color(0xFF25D366)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun JnkBottomNavBar(
    currentScreen: JnkScreen,
    onScreenSelected: (JnkScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.height(68.dp),
        containerColor = DarkCharcoal,
        contentColor = IvoryWhite,
        tonalElevation = 8.dp
    ) {
        JnkScreen.entries.forEach { screen ->
            val selected = currentScreen == screen
            val icon = when (screen) {
                JnkScreen.Home -> if (selected) Icons.Filled.Home else Icons.Outlined.Home
                JnkScreen.Portfolio -> if (selected) Icons.Filled.Grid3x3 else Icons.Outlined.Grid3x3
                JnkScreen.Estimator -> if (selected) Icons.Filled.Calculate else Icons.Outlined.Calculate
                JnkScreen.Consultation -> if (selected) Icons.Filled.EventNote else Icons.Outlined.EventNote
                JnkScreen.AiDesigner -> if (selected) Icons.Filled.AutoAwesome else Icons.Outlined.AutoAwesome
            }

            NavigationBarItem(
                selected = selected,
                onClick = { onScreenSelected(screen) },
                icon = {
                    if (screen == JnkScreen.AiDesigner) {
                        BadgedBox(
                            badge = {
                                Badge(
                                    containerColor = MatteGold,
                                    contentColor = DarkCharcoal
                                ) {
                                    Text("AI", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        ) {
                            Icon(imageVector = icon, contentDescription = screen.title)
                        }
                    } else {
                        Icon(imageVector = icon, contentDescription = screen.title)
                    }
                },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 10.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkCharcoal,
                    selectedTextColor = MatteGold,
                    indicatorColor = MatteGold,
                    unselectedIconColor = PuttyBeige,
                    unselectedTextColor = PuttyBeige
                )
            )
        }
    }
}
