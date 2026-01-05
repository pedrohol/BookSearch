package com.example.livros.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    suspend fun addFavorite (favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite (favorite: FavoriteEntity)

    @Query("SELECT * FROM favorites ORDER BY id ASC")
    fun getAllFavorites() : LiveData<List<FavoriteEntity>>

}