package com.example.livros.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livros.repository.SearchBookRepository

class SearchBookViewModelFactory(val repository: SearchBookRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return if(modelClass.isAssignableFrom(SearchBookViewModel::class.java)) {
           SearchBookViewModel(this.repository) as T
       } else {
           throw IllegalArgumentException("ViewModel not found")
       }
    }

}