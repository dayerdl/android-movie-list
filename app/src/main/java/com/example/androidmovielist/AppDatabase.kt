package com.example.androidmovielist

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidmovielist.data.database.MovieDao
import com.example.androidmovielist.data.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}