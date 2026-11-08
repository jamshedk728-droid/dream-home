package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.JnkCompanyInfo
import com.example.model.JnkData
import com.example.model.PortfolioItem
import com.example.ui.JnkViewModel
import com.example.ui.components.HeroInteriorCanvasBanner
import com.example.ui.components.JnkScreen
import com.example.ui.components.PortfolioCard
import com.example.ui.components.ProcessTimeline
import com.example.ui.components.TestimonialCarousel
import com.example.ui.components.WhyChooseUsGrid
import com.example.ui.theme.DarkCharcoal
import com.example.ui.theme.IvoryWhite
import com.example.ui.theme.MatteGold
import com.example.ui.theme.PuttyBeige
import com.example.ui.theme.WalnutBrown

@Composable
fun HomeScreen(
    viewModel: JnkViewModel,
    onNavigateToScreen: (JnkScreen) -> Unit,
    onInspectItem: (PortfolioItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(IvoryWhite)
    ) {
        // 1. HERO SECTION
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkCharcoal)
            ) {
                Column {
                    HeroInteriorCanvasBanner()

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 16.dp)
                    ) {
                        Surface(
                            color = MatteGold.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(
                                text = "TUNI'S PREMIER INTERIOR DESIGN STUDIO",
                                color = MatteGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Designing Beautiful Spaces That Inspire Every Day.",
                            color = IvoryWhite,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            lineHeight = 30.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Luxury Interior Design • POP Ceiling • False Ceiling • Modular Kitchen • Custom Furniture • Residential & Commercial Interiors",
                            color = PuttyBeige,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { onNavigateToScreen(JnkScreen.Consultation) },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MatteGold,
                                    contentColor = DarkCharcoal
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Get Free Consultation", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = { onNavigateToScreen(JnkScreen.Portfolio) },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = IvoryWhite),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("View Portfolio", fontSize = 12.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Stats Counters Bar
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(WalnutBrown.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            StatItem(JnkCompanyInfo.PROJECTS_COMPLETED, "Projects")
                            StatItem(JnkCompanyInfo.HAPPY_CLIENTS, "Clients")
                            StatItem(JnkCompanyInfo.YEARS_EXPERIENCE, "Years Exp")
                            StatItem(JnkCompanyInfo.DESIGNS_DELIVERED, "Designs")
                        }
                    }
                }
            }
        }

        // 2. ABOUT JNK INTERIOR DESIGNER
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "ABOUT JNK INTERIOR DESIGNER",
                    color = WalnutBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Crafting Luxury Homes & Functional Spaces in Tuni & Beyond",
                    color = DarkCharcoal,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "JNK Interior Designer is a trusted leader in residential & commercial interior architecture. We specialize in custom POP ceilings, false ceilings, high-gloss modular kitchens, space planning, and handcrafted teak wood furniture.",
                    color = Color(0xFF4A4A4A),
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Capabilities Pills
                val capabilities = listOf(
                    "Home Interiors", "Commercial Interiors", "POP Ceiling", "False Ceiling",
                    "Modular Kitchen", "TV Units", "Wardrobes", "Custom Furniture",
                    "Living Room Design", "Bedroom Design", "Dining Area", "Renovation"
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    capabilities.take(3).forEach { cap ->
                        Surface(
                            color = PuttyBeige.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(
                                text = "✓ $cap",
                                color = WalnutBrown,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }
            }
        }

        // 3. SERVICES SECTION
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkCharcoal)
                    .padding(vertical = 24.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = "OUR SERVICES",
                    color = MatteGold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Comprehensive Interior & Furniture Expertise",
                    color = IvoryWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(16.dp))

                JnkData.serviceCategories.forEach { service ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .clickable { onNavigateToScreen(JnkScreen.Consultation) },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF222222))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = service.title,
                                    color = IvoryWhite,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Serif
                                )

                                Surface(
                                    color = MatteGold.copy(alpha = 0.15f),
                                    shape = CircleShape
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "Learn More",
                                        tint = MatteGold,
                                        modifier = Modifier
                                            .size(28.dp)
                                            .padding(6.dp)
                                    )
                                }
                            }

                            Text(
                                text = service.subtitle,
                                color = MatteGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = service.description,
                                color = PuttyBeige,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // 4. POP CEILING & FALSE CEILING GALLERY HIGHLIGHT
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "POP CEILING MASTERCRAFT",
                            color = WalnutBrown,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "False Ceiling & LED Lighting",
                            color = DarkCharcoal,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.selectCategory("POP Ceiling")
                            onNavigateToScreen(JnkScreen.Portfolio)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = WalnutBrown)
                    ) {
                        Text("View All", fontSize = 11.sp)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                val popItems = JnkData.portfolioItems.filter { it.isPopCeilingSpecial || it.category == "POP Ceiling" }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(popItems) { item ->
                        PortfolioCard(
                            item = item,
                            isFavorite = false,
                            onFavoriteToggle = { viewModel.toggleFavorite(item, false) },
                            onClick = { onInspectItem(item) },
                            modifier = Modifier.width(260.dp)
                        )
                    }
                }
            }
        }

        // 5. CUSTOM FURNITURE SHOWCASE
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PuttyBeige.copy(alpha = 0.2f))
                    .padding(vertical = 24.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = "CUSTOM FURNITURE SHOWCASE",
                    color = WalnutBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Text(
                    text = "Modular Kitchens, Wardrobes & TV Units",
                    color = DarkCharcoal,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(14.dp))

                val furnitureItems = JnkData.portfolioItems.filter { it.isFurnitureSpecial || it.category == "Furniture" || it.category == "Kitchen" }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(furnitureItems) { item ->
                        PortfolioCard(
                            item = item,
                            isFavorite = false,
                            onFavoriteToggle = { viewModel.toggleFavorite(item, false) },
                            onClick = { onInspectItem(item) },
                            modifier = Modifier.width(260.dp)
                        )
                    }
                }
            }
        }

        // 6. WHY CHOOSE US
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "WHY CHOOSE US",
                    color = WalnutBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Uncompromising Craftsmanship & Transparency",
                    color = DarkCharcoal,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(14.dp))

                WhyChooseUsGrid(items = JnkData.whyChooseUs)
            }
        }

        // 7. WORKING PROCESS TIMELINE
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkCharcoal)
                    .padding(20.dp)
            ) {
                Text(
                    text = "OUR WORKING PROCESS",
                    color = MatteGold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "7 Seamless Steps From Concept to Handover",
                    color = IvoryWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProcessTimeline(steps = JnkData.processSteps)
            }
        }

        // 8. TESTIMONIALS
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = "CLIENT TESTIMONIALS",
                    color = WalnutBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "What Homeowners Say About JNK Interior",
                    color = DarkCharcoal,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(14.dp))

                TestimonialCarousel(testimonials = JnkData.testimonials)
            }
        }

        // 9. LOCATION & CONTACT CARD
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCharcoal)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "VISIT OUR STUDIO & SHOP",
                        color = MatteGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = JnkCompanyInfo.NAME,
                        color = IvoryWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Address",
                            tint = MatteGold,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = JnkCompanyInfo.ADDRESS,
                            color = PuttyBeige,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Phone",
                            tint = MatteGold,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = JnkCompanyInfo.PHONE,
                            color = IvoryWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { viewModel.callJnk(context) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = MatteGold, contentColor = DarkCharcoal)
                        ) {
                            Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Call Now", fontSize = 12.sp)
                        }

                        Button(
                            onClick = { viewModel.openMapLocation(context) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = WalnutBrown, contentColor = IvoryWhite)
                        ) {
                            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Map View", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = MatteGold,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = FontFamily.Serif
        )
        Text(
            text = label,
            color = IvoryWhite,
            fontSize = 11.sp
        )
    }
}
