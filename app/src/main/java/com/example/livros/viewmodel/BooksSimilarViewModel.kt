package com.example.livros.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.livros.model.BookSimilar
import com.example.livros.repository.BooksRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksSimilarViewModel(val repository: BooksRepository) : ViewModel() {

    val booksSimilar = MutableLiveData<BookSimilar>()
    val errorMessage = MutableLiveData<String>()

    fun getBookSimilars(bookId: Long) {

        val request = repository.getSimilar(bookId)
        request.enqueue(object : Callback<BookSimilar>{
            override fun onResponse(call: Call<BookSimilar?>, response: Response<BookSimilar?>) {
                booksSimilar.postValue(response.body())
            }

            override fun onFailure(call: Call<BookSimilar?>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

    }
}