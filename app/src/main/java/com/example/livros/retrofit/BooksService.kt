package com.example.livros.retrofit

import com.example.livros.model.BookInformation
import com.example.livros.model.Books
import com.example.livros.model.SearchBooks
import com.example.livros.util.Constants
import com.google.gson.Gson
import kotlinx.serialization.builtins.BooleanArraySerializer
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksService {

    @GET("search-books")
    fun getBooks(@Query("api-key") apiKey: String) : Call<SearchBooks>

    @GET("{id}")
    fun getBooksInformation(@Path("id") bookId: Long?, @Query("api-key") apiKey: String) : Call<BookInformation>

    companion object {

        private val apiURL = Constants.BASE_URL

        private val booksService: BooksService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl(apiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(BooksService::class.java)

        }

        fun getInstance() : BooksService {
            return booksService
        }

    }

}