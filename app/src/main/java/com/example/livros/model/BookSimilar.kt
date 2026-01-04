package com.example.livros.model

import com.google.gson.annotations.SerializedName

data class BookSimilar(
    @SerializedName("similar_books")
    val similarBooks: List<SimilarData>,
)
