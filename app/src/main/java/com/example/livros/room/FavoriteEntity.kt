package com.example.livros.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: String,
    val image: String,

)
