package com.example.livros.retrofit

import com.example.livros.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        private val apiURL = Constants.BASE_URL

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(apiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}