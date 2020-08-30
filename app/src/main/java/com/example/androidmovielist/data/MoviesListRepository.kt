package com.example.androidmovielist.data

import com.example.androidmovielist.api.MoviesService

class MoviesListRepository {
    val service = MoviesService.getClient()?.create(MoviesService::class.java)

    fun loadMovieList() {
        service?.getTopRatedMovies(1)
    }
}