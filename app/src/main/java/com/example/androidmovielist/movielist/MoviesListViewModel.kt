package com.example.androidmovielist.movielist

import com.example.androidmovielist.data.MoviesListRepository

class MoviesListViewModel constructor(private val repository: MoviesListRepository = MoviesListRepository()) {

    fun loadMoviesList(){
        repository.loadMovieList()
    }
}