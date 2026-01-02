package com.example.livros.model

data class Books(
    val id: Long,
    val title: String,
    val subtitle: String,
    val image: String,
    val authors: List<Authors>,
    val rating: Rating
    )

