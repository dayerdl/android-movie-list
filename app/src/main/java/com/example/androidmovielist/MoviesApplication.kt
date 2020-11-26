package com.example.androidmovielist

import android.app.Application
import androidx.room.Room
import com.example.androidmovielist.data.database.AppDatabase
import com.example.injector.Injector
import com.example.moviedetail.MovieDetailActivity

class MoviesApplication : Application(), Injector {

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

    override fun inject(any: Any) {
        when(any) {
            is MovieDetailActivity -> {
                any.moviesRepository = com.example.androidmovielist.di.Injector.repository
            }
        }
    }
}