package com.example.livros.repository

import com.example.livros.retrofit.BooksService
import com.example.livros.util.Constants

class SearchBookRepository(private val booksService: BooksService) {

     fun getBookList() = booksService.getBooks(Constants.API_KEY)

}