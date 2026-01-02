package com.example.livros.repository

import com.example.livros.retrofit.BooksService
import com.example.livros.retrofit.RetrofitInstance
import com.example.livros.util.Constants

class SearchBookRepository(val booksService: BooksService) {

    suspend fun getBooks() = booksService.getBooks(Constants.API_KEY)

}