package com.example.androidmovielist.data

import android.content.Context
import com.example.androidmovielist.data.database.AppDatabase
import com.example.androidmovielist.data.database.LocalMovie
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

class MoviesCache @Inject constructor(private val context: Context) {

    fun insertMovie(movie: LocalMovie) {
       runBlocking { AppDatabase.getInstance(context).movieDao().insertAll(movie) }
    }

    fun loadMovies(): Single<List<LocalMovie>> {
        return AppDatabase.getInstance(context).movieDao().getAll()
    }

}
