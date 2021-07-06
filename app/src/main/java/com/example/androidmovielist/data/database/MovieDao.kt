package com.example.androidmovielist.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM "+"movie")
    fun getAll(): LiveData<List<LocalMovie>>

    @Query("SELECT * FROM " + " movie WHERE id IN (:moviesId)")
    fun loadAllByIds(moviesId: IntArray): LiveData<List<LocalMovie>>

//    @Query("SELECT * FROM movie WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): LiveData<LocalMovie>

    @Insert
    suspend fun insertAll(vararg movies: LocalMovie)

    @Delete
    suspend fun delete(movie: LocalMovie)
}