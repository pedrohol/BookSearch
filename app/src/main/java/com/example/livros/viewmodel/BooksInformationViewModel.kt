package com.example.livros.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.livros.model.BookInformation
import com.example.livros.repository.BooksRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksInformationViewModel(val repository: BooksRepository) : ViewModel() {

    val bookInformation = MutableLiveData<BookInformation>()
    val errorMessage = MutableLiveData<String>()

    fun getBookInformation(bookId: Long?) {

        val request = repository.getInformation(bookId)
        request.enqueue(object : Callback<BookInformation> {
            override fun onResponse(call: Call<BookInformation?>, response: Response<BookInformation?>) {
                bookInformation.postValue(response.body())
            }

            override fun onFailure(call: Call<BookInformation?>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}