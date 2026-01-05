package com.example.livros.repository

import androidx.lifecycle.LiveData
import com.example.livros.room.FavoriteDao
import com.example.livros.room.FavoriteEntity

class FavoritesRepository(private val favoriteDao: FavoriteDao) {

    val readFavorites: LiveData<List<FavoriteEntity>> = favoriteDao.getAllFavorites()

    suspend fun addFavorite(favorite: FavoriteEntity) {
        favoriteDao.addFavorite(favorite)
    }

}