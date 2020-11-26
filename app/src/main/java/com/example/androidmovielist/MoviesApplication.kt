package com.example.androidmovielist

import android.app.Application
import androidx.room.Room
import com.example.androidmovielist.data.database.AppDatabase
import com.example.androidmovielist.di.DaggerAppComponent

class MoviesApplication : Application() {
    val appComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        createDataBase()
    }

    private fun createDataBase() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "movies"
        ).build()
    }
}