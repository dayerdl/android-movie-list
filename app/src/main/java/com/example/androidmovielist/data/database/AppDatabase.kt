package com.example.androidmovielist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalMovie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}