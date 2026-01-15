package com.example.livros.viewmodel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livros.repository.FavoritesRepository
import com.example.livros.viewmodel.FavoritesViewModel

class FavoritesViewModelFactory(private val repository: FavoritesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            FavoritesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}