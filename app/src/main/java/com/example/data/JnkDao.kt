package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JnkDao {
    @Query("SELECT * FROM favorite_designs ORDER BY timestamp DESC")
    fun getAllFavorites(): Flow<List<FavoriteDesign>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_designs WHERE id = :id)")
    fun isFavorite(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteDesign)

    @Query("DELETE FROM favorite_designs WHERE id = :id")
    suspend fun deleteFavoriteById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: ConsultationBooking)

    @Query("SELECT * FROM consultation_bookings ORDER BY timestamp DESC")
    fun getAllBookings(): Flow<List<ConsultationBooking>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstimate(estimate: SavedEstimate)

    @Query("SELECT * FROM saved_estimates ORDER BY timestamp DESC")
    fun getAllSavedEstimates(): Flow<List<SavedEstimate>>
}
