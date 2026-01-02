package com.example.livros.retrofit

import com.example.livros.model.SearchBooks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksService {

    @GET("search")
    suspend fun getBooks(@Query("api-key") apiKey: String) : Response<SearchBooks>

}