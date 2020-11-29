package com.example.androidmovielist.data

import com.example.androidmovielist.data.api.MoviesApiManager
import com.example.domainlayer.IMovie
import com.example.domainlayer.IMoviesRepository
import com.example.domainlayer.ITopRatedResults
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