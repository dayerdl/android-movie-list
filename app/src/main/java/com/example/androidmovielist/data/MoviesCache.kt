package com.example.androidmovielist.data

import android.content.Context
import com.example.androidmovielist.data.database.AppDatabase
import com.example.androidmovielist.data.database.LocalMovie
import javax.inject.Inject

class MoviesCache @Inject constructor(private val context: Context) {

    fun insertMovie(movie: LocalMovie) {
        AppDatabase.getInstance(context).movieDao().insertAll(movie)
    }

    fun loadMovies(): List<LocalMovie> {
        return AppDatabase.getInstance(context).movieDao().getAll()
    }

}
