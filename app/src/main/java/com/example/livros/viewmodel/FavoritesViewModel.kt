package com.example.livros.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livros.repository.FavoritesRepository
import com.example.livros.room.FavoriteDao
import com.example.livros.room.FavoriteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class FavoritesViewModel(private val repository: FavoritesRepository) : ViewModel() {

    val allFavorites: LiveData<List<FavoriteEntity>> = repository.readFavorites

    fun addFavorite (favorite: FavoriteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavorite(favorite)
        }
    }

    fun removeFavorite (favorite: FavoriteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(favorite)
        }
    }

}