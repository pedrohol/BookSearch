package com.example.livros.model

import android.accessibilityservice.GestureDescription

data class BookInformation (
    val id: Long,
    val title: String,
    val authors: Authors,
    val description: String,
)
