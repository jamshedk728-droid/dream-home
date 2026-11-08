package com.example.ui

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.ConsultationBooking
import com.example.data.FavoriteDesign
import com.example.data.JnkRepository
import com.example.data.SavedEstimate
import com.example.model.JnkCompanyInfo
import com.example.model.JnkData
import com.example.model.PortfolioItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class EstimatorState(
    val propertyType: String = "3BHK Flat",
    val popCeilingAreaSqFt: Float = 800f,
    val includeModularKitchen: Boolean = true,
    val kitchenAreaSqFt: Float = 120f,
    val includeCustomWardrobes: Boolean = true,
    val wardrobeSqFt: Float = 150f,
    val includeTvConsole: Boolean = true,
    val qualityTier: String = "Luxury Royal" // Standard, Premium, Luxury Royal
) {
    val estimatedPopCeilingCost: Int
        get() {
            val rate = when (qualityTier) {
                "Standard" -> 75
                "Premium" -> 110
                else -> 150
            }
            return (popCeilingAreaSqFt * rate).toInt()
        }

    val estimatedKitchenCost: Int
        get() = if (includeModularKitchen) {
            val rate = when (qualityTier) {
                "Standard" -> 1200
                "Premium" -> 1600
                else -> 2100
            }
            (kitchenAreaSqFt * rate).toInt()
        } else 0

    val estimatedWardrobeCost: Int
        get() = if (includeCustomWardrobes) {
            val rate = when (qualityTier) {
                "Standard" -> 1100
                "Premium" -> 1450
                else -> 1900
            }
            (wardrobeSqFt * rate).toInt()
        } else 0

    val estimatedTvConsoleCost: Int
        get() = if (includeTvConsole) {
            when (qualityTier) {
                "Standard" -> 35000
                "Premium" -> 55000
                else -> 85000
            }
        } else 0

    val totalMin: Int get() = (estimatedPopCeilingCost + estimatedKitchenCost + estimatedWardrobeCost + estimatedTvConsoleCost)
    val totalMax: Int get() = (totalMin * 1.25).toInt()
}

class JnkViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: JnkRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = JnkRepository(db.jnkDao())
    }

    val favoritesList: StateFlow<List<FavoriteDesign>> = repository.favoriteDesigns
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val savedEstimates: StateFlow<List<SavedEstimate>> = repository.savedEstimates
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val consultationBookings: StateFlow<List<ConsultationBooking>> = repository.consultationBookings
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Filter & Search State for Portfolio
    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Selected portfolio item for Lightbox dialog
    private val _inspectedItem = MutableStateFlow<PortfolioItem?>(null)
    val inspectedItem: StateFlow<PortfolioItem?> = _inspectedItem.asStateFlow()

    // Cost Estimator State
    private val _estimatorState = MutableStateFlow(EstimatorState())
    val estimatorState: StateFlow<EstimatorState> = _estimatorState.asStateFlow()

    // AI Designer Chat/Assistant state
    private val _aiRoomType = MutableStateFlow("Living Room")
    val aiRoomType: StateFlow<String> = _aiRoomType.asStateFlow()

    private val _aiBudget = MutableStateFlow("Medium (₹2 - 5 Lakhs)")
    val aiBudget: StateFlow<String> = _aiBudget.asStateFlow()

    private val _aiStyle = MutableStateFlow("Modern Royal Gold & Walnut")
    val aiStyle: StateFlow<String> = _aiStyle.asStateFlow()

    private val _aiSuggestionResult = MutableStateFlow<String?>(null)
    val aiSuggestionResult: StateFlow<String?> = _aiSuggestionResult.asStateFlow()

    // Filtered Portfolio list
    val filteredPortfolio: StateFlow<List<PortfolioItem>> = combine(
        _selectedCategory,
        _searchQuery
    ) { category, query ->
        JnkData.portfolioItems.filter { item ->
            val matchesCategory = (category == "All") || 
                    (category == "POP Ceiling" && (item.category == "POP Ceiling" || item.isPopCeilingSpecial)) ||
                    (category == "Furniture" && (item.category == "Furniture" || item.isFurnitureSpecial)) ||
                    item.category.equals(category, ignoreCase = true)

            val matchesQuery = query.isEmpty() ||
                    item.title.contains(query, ignoreCase = true) ||
                    item.description.contains(query, ignoreCase = true) ||
                    item.category.contains(query, ignoreCase = true) ||
                    item.roomType.contains(query, ignoreCase = true)

            matchesCategory && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), JnkData.portfolioItems)

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun inspectItem(item: PortfolioItem?) {
        _inspectedItem.value = item
    }

    fun isItemFavorite(id: String): StateFlow<Boolean> {
        return repository.isFavorite(id)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    }

    fun toggleFavorite(item: PortfolioItem, currentlyFavorite: Boolean) {
        viewModelScope.launch {
            val fav = FavoriteDesign(
                id = item.id,
                title = item.title,
                category = item.category,
                description = item.description,
                featuresJson = item.features.joinToString(",")
            )
            repository.toggleFavorite(fav, currentlyFavorite)
        }
    }

    // Estimator setters
    fun updateEstimatorPropertyType(type: String) {
        _estimatorState.value = _estimatorState.value.copy(propertyType = type)
    }

    fun updatePopArea(sqFt: Float) {
        _estimatorState.value = _estimatorState.value.copy(popCeilingAreaSqFt = sqFt)
    }

    fun updateKitchenArea(sqFt: Float) {
        _estimatorState.value = _estimatorState.value.copy(kitchenAreaSqFt = sqFt)
    }

    fun updateWardrobeSqFt(sqFt: Float) {
        _estimatorState.value = _estimatorState.value.copy(wardrobeSqFt = sqFt)
    }

    fun toggleModularKitchen(enable: Boolean) {
        _estimatorState.value = _estimatorState.value.copy(includeModularKitchen = enable)
    }

    fun toggleWardrobes(enable: Boolean) {
        _estimatorState.value = _estimatorState.value.copy(includeCustomWardrobes = enable)
    }

    fun toggleTvConsole(enable: Boolean) {
        _estimatorState.value = _estimatorState.value.copy(includeTvConsole = enable)
    }

    fun updateQualityTier(tier: String) {
        _estimatorState.value = _estimatorState.value.copy(qualityTier = tier)
    }

    fun saveCurrentEstimate() {
        viewModelScope.launch {
            val s = _estimatorState.value
            val est = SavedEstimate(
                title = "${s.propertyType} Interior Estimate",
                propertyType = s.propertyType,
                areaSqFt = s.popCeilingAreaSqFt.toInt(),
                popCeilingCost = s.estimatedPopCeilingCost,
                modularKitchenCost = s.estimatedKitchenCost,
                furnitureCost = s.estimatedWardrobeCost + s.estimatedTvConsoleCost,
                totalEstimateMin = s.totalMin,
                totalEstimateMax = s.totalMax
            )
            repository.saveEstimate(est)
        }
    }

    // AI Consultant generator
    fun setAiRoomType(type: String) { _aiRoomType.value = type }
    fun setAiBudget(budget: String) { _aiBudget.value = budget }
    fun setAiStyle(style: String) { _aiStyle.value = style }

    fun generateAiDesignPlan() {
        val room = _aiRoomType.value
        val budget = _aiBudget.value
        val style = _aiStyle.value

        val plan = """
            ✨ JNK AI Interior Design Proposal for $room ✨
            
            🎨 Color Palette & Vibe ($style):
            - Primary Tone: Warm Putty Beige with Ivory White Walls
            - Wood Finish: Deep Walnut Brown with Gold Metal Trim
            - Ceiling Accent: Matte Gold Linear LED Profiles
            
            💡 POP & Ceiling Recommendation:
            - Step False Ceiling with indirect LED cove lights (3000K Warm White)
            - Center Magnetic Track lights for adjustable spotlights
            
            🪑 Custom Furniture & Space Plan:
            - Customized storage solution using Termite-Proof Marine Plywood
            - Integrated hidden cabling for TV/Media console
            - Soft-close hardware & high-gloss acrylic surfaces
            
            💰 Estimated Investment Range: $budget
            📍 Location Service: Tuni, AP & Surrounding Areas (Site Survey Included)
        """.trimIndent()

        _aiSuggestionResult.value = plan
    }

    // Booking Submission
    fun submitBooking(
        name: String,
        phone: String,
        service: String,
        sqFt: String,
        budget: String,
        address: String,
        notes: String,
        context: Context
    ) {
        val sqFtInt = sqFt.toIntOrNull() ?: 500
        viewModelScope.launch {
            repository.addBooking(
                ConsultationBooking(
                    clientName = name,
                    phoneNumber = phone,
                    serviceType = service,
                    estimatedAreaSqFt = sqFtInt,
                    estimatedBudget = budget,
                    projectAddress = address,
                    notes = notes
                )
            )
        }

        // Send via WhatsApp
        val text = """
            *NEW CONSULTATION REQUEST - JNK INTERIOR DESIGNER*
            ------------------------------------
            👤 Name: $name
            📞 Phone: $phone
            🛠 Service: $service
            📐 Est. Area: $sqFtInt sq.ft
            💵 Budget Range: $budget
            📍 Address: $address
            📝 Notes: $notes
            ------------------------------------
            Sent from JNK Interior App
        """.trimIndent()

        openWhatsApp(context, text)
    }

    // Actions
    private fun safeStart(context: Context, intent: Intent) {
        try {
            context.startActivity(intent)
        } catch (e: android.content.ActivityNotFoundException) {
            android.widget.Toast.makeText(
                context, "No app found to open this action", android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun callJnk(context: Context) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${JnkCompanyInfo.PHONE}"))
        safeStart(context, intent)
    }

    fun openWhatsApp(context: Context, text: String = "Hello JNK Interior Designer, I would like to get a free consultation for my home/office interior.") {
        val url = "https://api.whatsapp.com/send?phone=${JnkCompanyInfo.WHATSAPP_NUMBER}&text=${Uri.encode(text)}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        safeStart(context, intent)
    }

    fun openInstagram(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(JnkCompanyInfo.INSTAGRAM_URL))
        safeStart(context, intent)
    }

    fun openMapLocation(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(JnkCompanyInfo.MAP_GEO_URI))
        safeStart(context, intent)
    }
}
