package com.example.androidmovielist.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM "+"movie")
    fun getAll(): List<LocalMovie>

    @Query("SELECT * FROM " + " movie WHERE uid IN (:moviesId)")
    fun loadAllByIds(moviesId: IntArray): List<LocalMovie>

    @Query("SELECT * FROM movie WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): LocalMovie

    @Insert
    fun insertAll(vararg movies: LocalMovie)

    @Delete
    fun delete(movie: LocalMovie)
}