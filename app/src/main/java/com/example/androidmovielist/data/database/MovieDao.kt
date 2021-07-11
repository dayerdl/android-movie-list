package com.example.androidmovielist.data.database

import androidx.room.*
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM "+"movie")
    fun getAll(): Single<List<LocalMovie>>

    @Query("SELECT * FROM " + " movie WHERE id IN (:moviesId)")
    fun loadAllByIds(moviesId: IntArray): List<LocalMovie>

//    @Query("SELECT * FROM movie WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): LiveData<LocalMovie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movie: LocalMovie)

    @Delete
    suspend fun delete(movie: LocalMovie)
}