package com.example.livros.model

import android.accessibilityservice.GestureDescription
import com.google.gson.annotations.SerializedName

data class BookInformation (

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("authors")
    val authors: List<Authors>,

    @SerializedName("publish_date")
    val publishDate: Int,

    @SerializedName("number_of_pages")
    val numberOfPages: Int,

    @SerializedName("description")
    val description: String,

)
