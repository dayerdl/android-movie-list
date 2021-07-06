package com.example.androidmovielist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalMovie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(applicationContext: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "movies"
                ).build()
            }
            return instance as AppDatabase
        }
    }

    abstract fun movieDao(): MovieDao

}