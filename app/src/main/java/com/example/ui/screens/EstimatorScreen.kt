package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.JnkViewModel
import com.example.ui.theme.DarkCharcoal
import com.example.ui.theme.IvoryWhite
import com.example.ui.theme.MatteGold
import com.example.ui.theme.PuttyBeige
import com.example.ui.theme.WalnutBrown
import java.text.NumberFormat
import java.util.Locale

@Composable
fun EstimatorScreen(
    viewModel: JnkViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val estimatorState by viewModel.estimatorState.collectAsStateWithLifecycle()
    val savedEstimates by viewModel.savedEstimates.collectAsStateWithLifecycle()

    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(IvoryWhite)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column {
                Text(
                    text = "INTERIOR COST ESTIMATOR",
                    color = WalnutBrown,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Text(
                    text = "Calculate Your Project Budget",
                    color = DarkCharcoal,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Text(
                    text = "Adjust square footage, ceiling coverage, kitchen & furniture options for instant estimates in Tuni, AP.",
                    color = Color(0xFF555555),
                    fontSize = 12.sp
                )
            }
        }

        // 1. Property Type & Tier Selection
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCharcoal)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("1. Property Type", color = MatteGold, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    val types = listOf("2BHK Flat", "3BHK Flat", "Independent House", "Villa", "Office")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        types.take(3).forEach { type ->
                            val selected = estimatorState.propertyType == type
                            FilterChip(
                                selected = selected,
                                onClick = { viewModel.updateEstimatorPropertyType(type) },
                                label = { Text(type, fontSize = 10.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MatteGold,
                                    selectedLabelColor = DarkCharcoal,
                                    containerColor = Color(0xFF2A2A2A),
                                    labelColor = PuttyBeige
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("2. Quality Finish Tier", color = MatteGold, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    val tiers = listOf("Standard", "Premium", "Luxury Royal")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        tiers.forEach { tier ->
                            val selected = estimatorState.qualityTier == tier
                            FilterChip(
                                selected = selected,
                                onClick = { viewModel.updateQualityTier(tier) },
                                label = { Text(tier, fontSize = 10.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MatteGold,
                                    selectedLabelColor = DarkCharcoal,
                                    containerColor = Color(0xFF2A2A2A),
                                    labelColor = PuttyBeige
                                )
                            )
                        }
                    }
                }
            }
        }

        // 2. Interactive Area Sliders
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCharcoal)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("3. Space & Furniture Quantities", color = MatteGold, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                    Spacer(modifier = Modifier.height(12.dp))

                    // POP Ceiling Slider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("POP & False Ceiling Area", color = IvoryWhite, fontSize = 12.sp)
                        Text("${estimatorState.popCeilingAreaSqFt.toInt()} sq.ft", color = MatteGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    Slider(
                        value = estimatorState.popCeilingAreaSqFt,
                        onValueChange = { viewModel.updatePopArea(it) },
                        valueRange = 200f..3000f,
                        colors = SliderDefaults.colors(thumbColor = MatteGold, activeTrackColor = MatteGold, inactiveTrackColor = WalnutBrown)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Kitchen Toggle & Slider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Modular Kitchen", color = IvoryWhite, fontSize = 12.sp)
                            if (estimatorState.includeModularKitchen) {
                                Text("${estimatorState.kitchenAreaSqFt.toInt()} sq.ft frontage", color = MatteGold, fontSize = 11.sp)
                            }
                        }
                        Switch(
                            checked = estimatorState.includeModularKitchen,
                            onCheckedChange = { viewModel.toggleModularKitchen(it) },
                            colors = SwitchDefaults.colors(checkedThumbColor = DarkCharcoal, checkedTrackColor = MatteGold)
                        )
                    }

                    if (estimatorState.includeModularKitchen) {
                        Slider(
                            value = estimatorState.kitchenAreaSqFt,
                            onValueChange = { viewModel.updateKitchenArea(it) },
                            valueRange = 50f..400f,
                            colors = SliderDefaults.colors(thumbColor = MatteGold, activeTrackColor = MatteGold, inactiveTrackColor = WalnutBrown)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Wardrobes Toggle & Slider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Custom Sliding Wardrobes", color = IvoryWhite, fontSize = 12.sp)
                            if (estimatorState.includeCustomWardrobes) {
                                Text("${estimatorState.wardrobeSqFt.toInt()} sq.ft wardrobe area", color = MatteGold, fontSize = 11.sp)
                            }
                        }
                        Switch(
                            checked = estimatorState.includeCustomWardrobes,
                            onCheckedChange = { viewModel.toggleWardrobes(it) },
                            colors = SwitchDefaults.colors(checkedThumbColor = DarkCharcoal, checkedTrackColor = MatteGold)
                        )
                    }

                    if (estimatorState.includeCustomWardrobes) {
                        Slider(
                            value = estimatorState.wardrobeSqFt,
                            onValueChange = { viewModel.updateWardrobeSqFt(it) },
                            valueRange = 50f..500f,
                            colors = SliderDefaults.colors(thumbColor = MatteGold, activeTrackColor = MatteGold, inactiveTrackColor = WalnutBrown)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // TV Unit Console Toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("TV Console & Louver Wall", color = IvoryWhite, fontSize = 12.sp)
                        Switch(
                            checked = estimatorState.includeTvConsole,
                            onCheckedChange = { viewModel.toggleTvConsole(it) },
                            colors = SwitchDefaults.colors(checkedThumbColor = DarkCharcoal, checkedTrackColor = MatteGold)
                        )
                    }
                }
            }
        }

        // 3. Itemized Cost Summary & Actions
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = WalnutBrown)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("ESTIMATED BUDGET BREAKDOWN", color = PuttyBeige, fontSize = 11.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(8.dp))

                    CostLine("POP & False Ceiling Work", currencyFormat.format(estimatorState.estimatedPopCeilingCost))
                    if (estimatorState.includeModularKitchen) {
                        CostLine("Modular Kitchen", currencyFormat.format(estimatorState.estimatedKitchenCost))
                    }
                    if (estimatorState.includeCustomWardrobes) {
                        CostLine("Custom Wardrobes", currencyFormat.format(estimatorState.estimatedWardrobeCost))
                    }
                    if (estimatorState.includeTvConsole) {
                        CostLine("TV Unit & Louver Wall", currencyFormat.format(estimatorState.estimatedTvConsoleCost))
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(color = PuttyBeige.copy(alpha = 0.3f), modifier = Modifier.fillMaxWidth().height(1.dp)) {}
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Estimated Total Range", color = PuttyBeige, fontSize = 11.sp)
                            Text(
                                "${currencyFormat.format(estimatorState.totalMin)} - ${currencyFormat.format(estimatorState.totalMax)}",
                                color = MatteGold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = { viewModel.saveCurrentEstimate() },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = IvoryWhite)
                        ) {
                            Icon(imageVector = Icons.Default.Save, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Save Estimate", fontSize = 11.sp)
                        }

                        Button(
                            onClick = {
                                val quoteText = """
                                    *JNK INTERIOR COST ESTIMATE*
                                    Property Type: ${estimatorState.propertyType}
                                    Tier: ${estimatorState.qualityTier}
                                    POP Ceiling: ${estimatorState.popCeilingAreaSqFt.toInt()} sq.ft (${currencyFormat.format(estimatorState.estimatedPopCeilingCost)})
                                    Modular Kitchen: ${currencyFormat.format(estimatorState.estimatedKitchenCost)}
                                    Wardrobes: ${currencyFormat.format(estimatorState.estimatedWardrobeCost)}
                                    TV Unit: ${currencyFormat.format(estimatorState.estimatedTvConsoleCost)}
                                    Estimated Total: ${currencyFormat.format(estimatorState.totalMin)} - ${currencyFormat.format(estimatorState.totalMax)}
                                    
                                    Please contact me to discuss site measurement in Tuni.
                                """.trimIndent()

                                viewModel.openWhatsApp(context, quoteText)
                            },
                            modifier = Modifier.weight(1.3f),
                            colors = ButtonDefaults.buttonColors(containerColor = MatteGold, contentColor = DarkCharcoal)
                        ) {
                            Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("WhatsApp Quote", fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }
                    }
                }
            }
        }

        // Saved Estimates List (Room DB)
        if (savedEstimates.isNotEmpty()) {
            item {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("SAVED ESTIMATES (LOCAL ROOM DB)", color = WalnutBrown, fontWeight = FontWeight.Bold, fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    savedEstimates.take(3).forEach { est ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = DarkCharcoal)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp).fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(est.title, color = IvoryWhite, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text("${est.areaSqFt} sq.ft ceiling area", color = PuttyBeige, fontSize = 11.sp)
                                }
                                Text(currencyFormat.format(est.totalEstimateMin), color = MatteGold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CostLine(label: String, amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = IvoryWhite, fontSize = 12.sp)
        Text(amount, color = PuttyBeige, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}
