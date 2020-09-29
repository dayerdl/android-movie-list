package com.example.androidmovielist.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM "+"movie")
    fun getAll(): List<LocalMovie>

    @Query("SELECT * FROM " + " user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<LocalMovie>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): LocalMovie

    @Insert
    fun insertAll(vararg users: LocalMovie)

    @Delete
    fun delete(user: LocalMovie)
}