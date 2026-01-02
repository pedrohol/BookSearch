package com.example.livros.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.livros.model.Books
import com.example.livros.model.SearchBooks

import com.example.livros.repository.SearchBookRepository
import com.example.livros.retrofit.BooksService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchBookViewModel(private val repository: SearchBookRepository) : ViewModel() {

    val booksList = MutableLiveData<SearchBooks>()
    val errorMessage = MutableLiveData<String>()

    fun getAllBooks() {

        val request = repository.getBookList()
        request.enqueue(object : Callback<SearchBooks> {
            override fun onResponse(call: Call<SearchBooks>?, response: Response<SearchBooks>?) {
                booksList.postValue(response?.body())
            }

            override fun onFailure(call: Call<SearchBooks>?, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }


}