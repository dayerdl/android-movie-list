package com.example.androidmovielist.data

import com.example.androidmovielist.data.api.MoviesApiManager
import com.example.androidmovielist.data.api.MoviesServiceApi
import com.example.androidmovielist.data.model.Movie
import com.example.androidmovielist.data.model.TopRatedResults
import io.reactivex.Single
import javax.inject.Inject

class MoviesListRepository @Inject constructor(private val serviceApi: MoviesApiManager) {

    fun loadTopMovies(): Single<TopRatedResults> {
        return Single.fromObservable(serviceApi.provideApiManager().getTopRatedMovies(1))
    }

    fun loadMovieDetails(movieId: Int): Single<Movie> {
        return Single.fromObservable(serviceApi.provideApiManager().getMovieDetails(movieId))
    }

}