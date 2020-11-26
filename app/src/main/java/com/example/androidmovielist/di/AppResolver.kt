package com.example.androidmovielist.di

import com.example.androidmovielist.data.MoviesListRepository
import com.example.androidmovielist.data.api.MoviesApiManager

object AppResolver {

    fun provideMovieSource(apiManager: MoviesApiManager) = apiManager

    fun provideMoviesRepository(serviceApi: MoviesApiManager) = MoviesListRepository(serviceApi)


}