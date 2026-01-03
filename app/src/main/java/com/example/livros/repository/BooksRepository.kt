package com.example.livros.repository

import com.example.livros.R
import com.example.livros.model.Authors
import com.example.livros.model.Books
import com.example.livros.retrofit.BooksService
import com.example.livros.util.Constants

class BooksRepository(private val booksService: BooksService) {

    fun getBookList() = booksService.getBooks(Constants.API_KEY)
    fun getInformation(bookId: Long?) = booksService.getBooksInformation(bookId, Constants.API_KEY)

}