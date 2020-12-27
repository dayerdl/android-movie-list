package com.example.androidmovielist.data

import com.example.androidmovielist.data.api.MoviesApiManager
import com.example.androidmovielist.data.model.Configuration
import com.example.domain.movies.IMovie
import com.example.domain.movies.IMoviesRepository
import com.example.domain.movies.ITopRatedResults
import io.reactivex.Single
import javax.inject.Inject

class MoviesListRepository @Inject constructor(private val serviceApi: MoviesApiManager) {

    fun getConfiguration(): Single<Configuration>{
        return Single.fromObservable(serviceApi.provideApiManager().getConfiguration())
    }

}