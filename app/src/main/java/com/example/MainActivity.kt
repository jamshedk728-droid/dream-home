package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.PortfolioItem
import com.example.ui.JnkViewModel
import com.example.ui.components.JnkBottomNavBar
import com.example.ui.components.JnkScreen
import com.example.ui.components.JnkTopBar
import com.example.ui.components.LightboxDesignInspectorDialog
import com.example.ui.screens.AiDesignerScreen
import com.example.ui.screens.ConsultationScreen
import com.example.ui.screens.EstimatorScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PortfolioScreen
import com.example.ui.theme.DarkCharcoal
import com.example.ui.theme.JnkTheme
import com.example.ui.theme.MatteGold

class MainActivity : ComponentActivity() {
    private val viewModel: JnkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            JnkTheme {
                JnkApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun JnkApp(viewModel: JnkViewModel) {
    val context = LocalContext.current
    var currentScreen by remember { mutableStateOf(JnkScreen.Home) }
    val inspectedItem by viewModel.inspectedItem.collectAsStateWithLifecycle()
    val favoritesList by viewModel.favoritesList.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            JnkTopBar(
                onCallClick = { viewModel.callJnk(context) },
                onWhatsAppClick = { viewModel.openWhatsApp(context) },
                onMapClick = { viewModel.openMapLocation(context) },
                onInstagramClick = { viewModel.openInstagram(context) }
            )
        },
        bottomBar = {
            JnkBottomNavBar(
                currentScreen = currentScreen,
                onScreenSelected = { currentScreen = it }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.openWhatsApp(context, "Hello JNK Interior Designer, I want to inquire about false ceiling and interior designs.") },
                containerColor = MatteGold,
                contentColor = DarkCharcoal
            ) {
                Icon(imageVector = Icons.Default.Chat, contentDescription = "WhatsApp JNK Studio")
            }
        },
        containerColor = DarkCharcoal
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "ScreenTransition"
            ) { screen ->
                when (screen) {
                    JnkScreen.Home -> HomeScreen(
                        viewModel = viewModel,
                        onNavigateToScreen = { currentScreen = it },
                        onInspectItem = { viewModel.inspectItem(it) }
                    )
                    JnkScreen.Portfolio -> PortfolioScreen(
                        viewModel = viewModel,
                        onInspectItem = { viewModel.inspectItem(it) }
                    )
                    JnkScreen.Estimator -> EstimatorScreen(
                        viewModel = viewModel
                    )
                    JnkScreen.Consultation -> ConsultationScreen(
                        viewModel = viewModel
                    )
                    JnkScreen.AiDesigner -> AiDesignerScreen(
                        viewModel = viewModel
                    )
                }
            }

            // Lightbox Dialog
            inspectedItem?.let { item ->
                val isFav = favoritesList.any { it.id == item.id }
                LightboxDesignInspectorDialog(
                    item = item,
                    isFavorite = isFav,
                    onFavoriteToggle = { viewModel.toggleFavorite(item, isFav) },
                    onDismiss = { viewModel.inspectItem(null) },
                    onConsultClick = {
                        viewModel.inspectItem(null)
                        currentScreen = JnkScreen.Consultation
                    }
                )
            }
        }
    }
}
