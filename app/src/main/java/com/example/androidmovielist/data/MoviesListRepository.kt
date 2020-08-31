package com.example.androidmovielist.data

import com.example.androidmovielist.api.MoviesService
import com.example.androidmovielist.model.TopRated
import io.reactivex.Single

class MoviesListRepository {
    val service = MoviesService.getClient().create(MoviesService::class.java)

    fun loadMovieList(): Single<TopRated> {
        return service.getTopRatedMovies(1)
    }
}