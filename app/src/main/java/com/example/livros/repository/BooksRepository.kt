package com.example.livros.repository

import com.example.livros.R
import com.example.livros.model.Authors
import com.example.livros.model.Books

class BooksRepository {

    val booksList = listOf<Books>(
        Books(123,"Livro 01", R.drawable.placeholder_book, arrayOf(Authors(46456460,"Autor 01"))),
        Books(1234,"Livro 02", R.drawable.placeholder_book, arrayOf(Authors(35435354,"Autor 02"))),
        Books(12345,"Livro 03", R.drawable.placeholder_book, arrayOf(Authors(35435300,"Autor 03"))),
        Books(123456,"Livro 04", R.drawable.placeholder_book, arrayOf(Authors(53535435,"Autor 04"))),
        Books(1234567,"Livro 05", R.drawable.placeholder_book, arrayOf(Authors(75675755,"Autor 05"))),
        )

}