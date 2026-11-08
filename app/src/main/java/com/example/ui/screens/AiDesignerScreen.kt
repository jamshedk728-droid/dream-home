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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiDesignerScreen(
    viewModel: JnkViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val roomType by viewModel.aiRoomType.collectAsStateWithLifecycle()
    val budget by viewModel.aiBudget.collectAsStateWithLifecycle()
    val style by viewModel.aiStyle.collectAsStateWithLifecycle()
    val suggestionResult by viewModel.aiSuggestionResult.collectAsStateWithLifecycle()

    var roomExpanded by remember { mutableStateOf(false) }
    var budgetExpanded by remember { mutableStateOf(false) }
    var styleExpanded by remember { mutableStateOf(false) }

    val roomOptions = listOf("Living Room", "Master Bedroom", "Modular Kitchen", "POP Ceiling Grid", "TV Unit Lounge", "Executive Office")
    val budgetOptions = listOf("Economy (Under ₹2 Lakhs)", "Medium (₹2 - 5 Lakhs)", "Luxury Royal (₹5 - 12 Lakhs)")
    val styleOptions = listOf("Modern Royal Gold & Walnut", "Minimalist Scandinavian Ivory", "Rustic Warm Wooden Rafters", "Sleek Marble & Charcoal")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DarkCharcoal)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = "AI", tint = MatteGold, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ASK JNK AI DESIGN CONSULTANT",
                        color = MatteGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Text(
                    text = "Instant Space & Ceiling Proposal",
                    color = IvoryWhite,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Text(
                    text = "Pick room type, budget and desired vibe to generate a customized interior layout proposal.",
                    color = PuttyBeige,
                    fontSize = 12.sp
                )
            }
        }

        // Selection Controls Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF222222))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    // Room Dropdown
                    ExposedDropdownMenuBox(
                        expanded = roomExpanded,
                        onExpandedChange = { roomExpanded = !roomExpanded }
                    ) {
                        OutlinedTextField(
                            value = roomType,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Target Space", color = PuttyBeige) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = roomExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MatteGold,
                                unfocusedBorderColor = WalnutBrown,
                                focusedTextColor = IvoryWhite,
                                unfocusedTextColor = IvoryWhite
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = roomExpanded,
                            onDismissRequest = { roomExpanded = false },
                            modifier = Modifier.background(DarkCharcoal)
                        ) {
                            roomOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option, color = IvoryWhite, fontSize = 12.sp) },
                                    onClick = {
                                        viewModel.setAiRoomType(option)
                                        roomExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Budget Dropdown
                    ExposedDropdownMenuBox(
                        expanded = budgetExpanded,
                        onExpandedChange = { budgetExpanded = !budgetExpanded }
                    ) {
                        OutlinedTextField(
                            value = budget,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Budget Range", color = PuttyBeige) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = budgetExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MatteGold,
                                unfocusedBorderColor = WalnutBrown,
                                focusedTextColor = IvoryWhite,
                                unfocusedTextColor = IvoryWhite
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = budgetExpanded,
                            onDismissRequest = { budgetExpanded = false },
                            modifier = Modifier.background(DarkCharcoal)
                        ) {
                            budgetOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option, color = IvoryWhite, fontSize = 12.sp) },
                                    onClick = {
                                        viewModel.setAiBudget(option)
                                        budgetExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Style Dropdown
                    ExposedDropdownMenuBox(
                        expanded = styleExpanded,
                        onExpandedChange = { styleExpanded = !styleExpanded }
                    ) {
                        OutlinedTextField(
                            value = style,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Design Vibe / Theme", color = PuttyBeige) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = styleExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MatteGold,
                                unfocusedBorderColor = WalnutBrown,
                                focusedTextColor = IvoryWhite,
                                unfocusedTextColor = IvoryWhite
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = styleExpanded,
                            onDismissRequest = { styleExpanded = false },
                            modifier = Modifier.background(DarkCharcoal)
                        ) {
                            styleOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option, color = IvoryWhite, fontSize = 12.sp) },
                                    onClick = {
                                        viewModel.setAiStyle(option)
                                        styleExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { viewModel.generateAiDesignPlan() },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MatteGold, contentColor = DarkCharcoal),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Generate Custom AI Plan", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }

        // Result Card
        suggestionResult?.let { resultText ->
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = WalnutBrown)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Lightbulb, contentDescription = null, tint = MatteGold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("YOUR AI PROPOSAL", color = PuttyBeige, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = resultText,
                            color = IvoryWhite,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { viewModel.openWhatsApp(context, resultText) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = MatteGold, contentColor = DarkCharcoal)
                        ) {
                            Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Discuss Plan on WhatsApp", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}
