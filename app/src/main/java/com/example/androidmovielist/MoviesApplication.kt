package com.example.androidmovielist

import androidx.room.Room
import com.example.androidmovielist.data.database.AppDatabase
import com.example.androidmovielist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MoviesApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        createDataBase()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.create()
    }

    private fun createDataBase() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "movies"
        ).build()
    }
}