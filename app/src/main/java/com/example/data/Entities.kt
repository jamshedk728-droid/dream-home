package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_designs")
data class FavoriteDesign(
    @PrimaryKey val id: String,
    val title: String,
    val category: String,
    val description: String,
    val featuresJson: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "consultation_bookings")
data class ConsultationBooking(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clientName: String,
    val phoneNumber: String,
    val serviceType: String,
    val estimatedAreaSqFt: Int,
    val estimatedBudget: String,
    val projectAddress: String,
    val notes: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_estimates")
data class SavedEstimate(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val propertyType: String, // 2BHK, 3BHK, Commercial, Ceiling Only
    val areaSqFt: Int,
    val popCeilingCost: Int,
    val modularKitchenCost: Int,
    val furnitureCost: Int,
    val totalEstimateMin: Int,
    val totalEstimateMax: Int,
    val timestamp: Long = System.currentTimeMillis()
)
