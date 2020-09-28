package com.example.androidmovielist.data

import com.example.androidmovielist.data.api.MoviesService
import com.example.androidmovielist.data.model.TopRated
import io.reactivex.Observable
import io.reactivex.Single

class MoviesListRepository {
    private val service = MoviesService.getClient().create(MoviesService::class.java)

    fun loadMovieList(): Single<TopRated> {
        return Single.fromObservable(service.getTopRatedMovies(1))
    }

    fun loadMoviesListObserver() : Observable<TopRated> {
        return service.getTopRatedMovies()
    }
}