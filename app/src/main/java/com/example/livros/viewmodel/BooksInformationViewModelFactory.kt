package com.example.livros.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livros.repository.BooksRepository

class BooksInformationViewModelFactory(val repository: BooksRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BooksInformationViewModel::class.java)) {
            BooksInformationViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("View Model not found")
        }
    }

}