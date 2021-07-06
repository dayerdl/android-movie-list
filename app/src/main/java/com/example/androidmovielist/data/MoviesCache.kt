package com.example.androidmovielist.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.androidmovielist.data.database.AppDatabase
import com.example.androidmovielist.data.database.LocalMovie
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MoviesCache @Inject constructor(private val context: Context) {

    fun insertMovie(movie: LocalMovie) {
        runBlocking { AppDatabase.getInstance(context).movieDao().insertAll(movie) }
    }

    fun loadMovies(): LiveData<List<LocalMovie>> {
        return AppDatabase.getInstance(context).movieDao().getAll()
    }

}
