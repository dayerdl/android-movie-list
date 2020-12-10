package com.example.androidmovielist.data

import com.example.androidmovielist.data.api.MoviesApiManager
import com.example.domain.movies.IMovie
import com.example.domain.movies.IMoviesRepository
import com.example.domain.movies.ITopRatedResults
import io.reactivex.Single
import javax.inject.Inject

class MoviesListRepository @Inject constructor(private val serviceApi: MoviesApiManager):
    IMoviesRepository {

    override fun loadTopMovies(): Single<ITopRatedResults> {
        return Single.fromObservable(serviceApi.provideApiManager().getTopRatedMovies(1))
    }

    override fun loadMovieDetails(movieId: Int): Single<IMovie> {
        return Single.fromObservable(serviceApi.provideApiManager().getMovieDetails(movieId))
    }

}