package com.example.androidmovielist.data

import androidx.lifecycle.LiveData
import com.example.androidmovielist.data.api.MoviesApiManager
import com.example.androidmovielist.data.database.LocalMovie
import com.example.domain.movies.IMovie
import com.example.domain.movies.IMoviesRepository
import com.example.domain.movies.ITopRatedResults
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val service: MoviesApiManager, private val cache: MoviesCache): IMoviesRepository {

    override fun loadTopMovies(): Single<ITopRatedResults> {
        return Single.fromObservable(service.provideApiManager().getTopRatedMovies(1))
    }

    override fun loadMovieDetails(movieId: Int): Single<IMovie> {
        return Single.fromObservable(service.provideApiManager().getMovieDetails(movieId))
    }

    fun saveMovie(movie: LocalMovie) {
        cache.insertMovie(movie)
    }

    fun loadUserSavedMovies(): LiveData<List<LocalMovie>> {
        return cache.loadMovies()
    }
}