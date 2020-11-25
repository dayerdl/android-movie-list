package com.example.androidmovielist.data

import com.example.androidmovielist.data.api.MoviesService
import com.example.androidmovielist.data.model.Movie
import com.example.androidmovielist.data.model.TopRatedResults
import io.reactivex.Single

class MoviesListRepository(private val service: MoviesService) {

    fun loadTopMovies(): Single<TopRatedResults> {
        return Single.fromObservable(service.getTopRatedMovies(1))
    }

    fun loadMovieDetails(movieId: Int): Single<Movie> {
        return Single.fromObservable(service.getMovieDetails(movieId))
    }

}