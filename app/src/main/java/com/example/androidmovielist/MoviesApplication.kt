package com.example.androidmovielist

import android.app.Application
import androidx.room.Room

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createDataBase()
    }

    private fun createDataBase() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "favourites-movies"
        ).build()
    }
}