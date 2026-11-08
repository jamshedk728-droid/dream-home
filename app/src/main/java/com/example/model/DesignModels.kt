package com.example.model

data class PortfolioItem(
    val id: String,
    val title: String,
    val category: String, // "Living Room", "Bedroom", "Kitchen", "POP Ceiling", "Furniture", "TV Units", "Wardrobes", "Commercial"
    val subcategory: String,
    val description: String,
    val sqFtPriceRange: String,
    val roomType: String,
    val features: List<String>,
    val gradientColorsHex: List<Long>,
    val isFeaturedHero: Boolean = false,
    val isPopCeilingSpecial: Boolean = false,
    val isFurnitureSpecial: Boolean = false
)

data class ServiceCategory(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val keyFeatures: List<String>,
    val iconName: String
)

data class ProcessStep(
    val stepNumber: Int,
    val title: String,
    val subtitle: String,
    val description: String
)

data class WhyChooseUsItem(
    val title: String,
    val description: String,
    val iconType: String
)

data class Testimonial(
    val clientName: String,
    val location: String,
    val rating: Int = 5,
    val review: String,
    val projectType: String,
    val date: String
)

object JnkCompanyInfo {
    const val NAME = "JNK INTERIOR DESIGNER"
    const val CATEGORY = "Interior Design & Furniture Work"
    const val PHONE = "+91 9613332999"
    const val WHATSAPP_NUMBER = "919613332999"
    const val ADDRESS = "136, Near Bhawani Fancy Store, Balaji Complex, Tuni, Andhra Pradesh – 533401"
    const val INSTAGRAM_URL = "https://www.instagram.com/jnk.interior19?igsh=dmtvc2ZrOGU2dWZp"
    const val MAP_GEO_URI = "geo:17.3551,82.5484?q=136,+Near+Bhawani+Fancy+Store,+Balaji+Complex,+Tuni,+Andhra+Pradesh+533401"
    
    // Stats
    const val PROJECTS_COMPLETED = "350+"
    const val HAPPY_CLIENTS = "320+"
    const val YEARS_EXPERIENCE = "12+"
    const val DESIGNS_DELIVERED = "500+"
}
