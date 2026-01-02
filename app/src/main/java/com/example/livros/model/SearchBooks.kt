package com.example.livros.model

data class SearchBooks(
    val available: Long,
    val number: Int,
    val offset: Int,
    val books: List<List<Books>>,
)
