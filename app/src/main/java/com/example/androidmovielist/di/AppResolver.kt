package com.example.androidmovielist.di

import com.example.androidmovielist.data.MoviesRepository
import com.example.androidmovielist.data.api.MoviesService

object AppResolver {

    fun provideMovieSource(apiManager: MoviesService.Companion) = apiManager.getClient().create(MoviesService::class.java)

    fun provideMoviesRepository(service: MoviesService) = MoviesRepository(service)


}