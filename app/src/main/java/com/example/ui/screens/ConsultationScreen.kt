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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.JnkCompanyInfo
import com.example.ui.JnkViewModel
import com.example.ui.theme.DarkCharcoal
import com.example.ui.theme.IvoryWhite
import com.example.ui.theme.MatteGold
import com.example.ui.theme.PuttyBeige
import com.example.ui.theme.WalnutBrown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationScreen(
    viewModel: JnkViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val bookings by viewModel.consultationBookings.collectAsStateWithLifecycle()

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var service by remember { mutableStateOf("Home Interior Design") }
    var sqFt by remember { mutableStateOf("1200") }
    var budget by remember { mutableStateOf("₹ 2 - 5 Lakhs") }
    var address by remember { mutableStateOf("Balaji Complex Area, Tuni") }
    var notes by remember { mutableStateOf("") }

    var serviceExpanded by remember { mutableStateOf(false) }
    var budgetExpanded by remember { mutableStateOf(false) }

    val servicesList = listOf(
        "Home Interior Design", "POP Ceiling & False Ceiling",
        "Modular Kitchen", "Custom Furniture & Wardrobes",
        "TV Unit & Wall Paneling", "Commercial & Office Interior", "Renovation & Remodeling"
    )

    val budgetOptions = listOf("Under ₹ 2 Lakhs", "₹ 2 - 5 Lakhs", "₹ 5 - 10 Lakhs", "₹ 10 - 20 Lakhs", "₹ 20+ Lakhs")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(IvoryWhite)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title
        item {
            Column {
                Text(
                    text = "BOOK A SITE VISIT & CONSULTATION",
                    color = WalnutBrown,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Text(
                    text = "Request Free On-Site Survey",
                    color = DarkCharcoal,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Text(
                    text = "Fill out the form to schedule a site measurement visit in Tuni, Kakinada, Rajahmundry, Vizag or nearby AP districts.",
                    color = Color(0xFF555555),
                    fontSize = 12.sp
                )
            }
        }

        // Form Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCharcoal)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "CONSULTATION FORM",
                        color = MatteGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Name Field
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Your Full Name", color = PuttyBeige) },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = MatteGold) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MatteGold,
                            unfocusedBorderColor = WalnutBrown,
                            focusedTextColor = IvoryWhite,
                            unfocusedTextColor = IvoryWhite
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Phone Field
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone Number (10 digits, required)", color = PuttyBeige) },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = MatteGold) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MatteGold,
                            unfocusedBorderColor = WalnutBrown,
                            focusedTextColor = IvoryWhite,
                            unfocusedTextColor = IvoryWhite
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Service Dropdown
                    ExposedDropdownMenuBox(
                        expanded = serviceExpanded,
                        onExpandedChange = { serviceExpanded = !serviceExpanded }
                    ) {
                        OutlinedTextField(
                            value = service,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Service Required", color = PuttyBeige) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = serviceExpanded) },
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MatteGold,
                                unfocusedBorderColor = WalnutBrown,
                                focusedTextColor = IvoryWhite,
                                unfocusedTextColor = IvoryWhite
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = serviceExpanded,
                            onDismissRequest = { serviceExpanded = false },
                            modifier = Modifier.background(DarkCharcoal)
                        ) {
                            servicesList.forEach { s ->
                                DropdownMenuItem(
                                    text = { Text(s, color = IvoryWhite, fontSize = 12.sp) },
                                    onClick = {
                                        service = s
                                        serviceExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Budget Dropdown & SqFt Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = sqFt,
                            onValueChange = { sqFt = it },
                            label = { Text("Area (Sq.Ft)", color = PuttyBeige) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MatteGold,
                                unfocusedBorderColor = WalnutBrown,
                                focusedTextColor = IvoryWhite,
                                unfocusedTextColor = IvoryWhite
                            )
                        )

                        ExposedDropdownMenuBox(
                            expanded = budgetExpanded,
                            onExpandedChange = { budgetExpanded = !budgetExpanded },
                            modifier = Modifier.weight(1.3f)
                        ) {
                            OutlinedTextField(
                                value = budget,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Budget", color = PuttyBeige) },
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
                                budgetOptions.forEach { b ->
                                    DropdownMenuItem(
                                        text = { Text(b, color = IvoryWhite, fontSize = 12.sp) },
                                        onClick = {
                                            budget = b
                                            budgetExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Address Field
                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Site Address / City", color = PuttyBeige) },
                        leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = MatteGold) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MatteGold,
                            unfocusedBorderColor = WalnutBrown,
                            focusedTextColor = IvoryWhite,
                            unfocusedTextColor = IvoryWhite
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Notes Field
                    OutlinedTextField(
                        value = notes,
                        onValueChange = { notes = it },
                        label = { Text("Special Design Requirements (Optional)", color = PuttyBeige) },
                        modifier = Modifier.fillMaxWidth().height(90.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MatteGold,
                            unfocusedBorderColor = WalnutBrown,
                            focusedTextColor = IvoryWhite,
                            unfocusedTextColor = IvoryWhite
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        enabled = name.isNotBlank() && phone.filter { it.isDigit() }.length >= 10,
                        onClick = {
                            viewModel.submitBooking(
                                name = name.trim(),
                                phone = phone.trim(),
                                service = service,
                                sqFt = sqFt,
                                budget = budget,
                                address = address,
                                notes = notes,
                                context = context
                            )
                        },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MatteGold, contentColor = DarkCharcoal),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Submit & Connect via WhatsApp", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }

        // Direct Call Option
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = WalnutBrown)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Prefer Direct Call?", color = IvoryWhite, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Speak with JNK chief designer now", color = PuttyBeige, fontSize = 11.sp)
                    }

                    Button(
                        onClick = { viewModel.callJnk(context) },
                        colors = ButtonDefaults.buttonColors(containerColor = MatteGold, contentColor = DarkCharcoal)
                    ) {
                        Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Call Now", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }

        // Past Local Bookings from Room DB
        if (bookings.isNotEmpty()) {
            item {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("YOUR RECENT INQUIRIES (ROOM PERSISTED)", color = WalnutBrown, fontWeight = FontWeight.Bold, fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    bookings.take(3).forEach { booking ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = DarkCharcoal)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(booking.clientName, color = IvoryWhite, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Surface(color = MatteGold.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp)) {
                                        Text(booking.serviceType, color = MatteGold, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Address: ${booking.projectAddress} • Area: ${booking.estimatedAreaSqFt} sq.ft", color = PuttyBeige, fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
