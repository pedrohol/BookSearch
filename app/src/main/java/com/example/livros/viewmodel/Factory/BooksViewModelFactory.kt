package com.example.livros.viewmodel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.livros.repository.BooksRepository
import com.example.livros.viewmodel.SearchBookViewModel

class BooksViewModelFactory(val repository: BooksRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return if(modelClass.isAssignableFrom(SearchBookViewModel::class.java)) {
           SearchBookViewModel(this.repository) as T
       } else {
           throw IllegalArgumentException("ViewModel not found")
       }
    }

}