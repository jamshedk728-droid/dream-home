package com.example.data

import kotlinx.coroutines.flow.Flow

class JnkRepository(private val dao: JnkDao) {
    val favoriteDesigns: Flow<List<FavoriteDesign>> = dao.getAllFavorites()
    val consultationBookings: Flow<List<ConsultationBooking>> = dao.getAllBookings()
    val savedEstimates: Flow<List<SavedEstimate>> = dao.getAllSavedEstimates()

    fun isFavorite(id: String): Flow<Boolean> = dao.isFavorite(id)

    suspend fun toggleFavorite(design: FavoriteDesign, currentlyFavorite: Boolean) {
        if (currentlyFavorite) {
            dao.deleteFavoriteById(design.id)
        } else {
            dao.insertFavorite(design)
        }
    }

    suspend fun addBooking(booking: ConsultationBooking) {
        dao.insertBooking(booking)
    }

    suspend fun saveEstimate(estimate: SavedEstimate) {
        dao.insertEstimate(estimate)
    }
}
