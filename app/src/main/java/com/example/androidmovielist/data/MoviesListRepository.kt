package com.example.androidmovielist.data

import com.example.androidmovielist.data.api.MoviesService
import com.example.androidmovielist.data.model.Movie
import com.example.androidmovielist.data.model.TopRatedResults
import io.reactivex.Observable
import io.reactivex.Single

class MoviesListRepository {
    private val service = MoviesService.getClient().create(MoviesService::class.java)

    fun loadTopMovies(): Single<TopRatedResults> {
        return Single.fromObservable(service.getTopRatedMovies(1))
    }

//    fun loadFavouriteMovies(): Single<List<Movie>> {
//        return Single.just(arrayListOf(null))
//    }
}