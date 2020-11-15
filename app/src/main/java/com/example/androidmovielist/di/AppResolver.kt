package com.example.androidmovielist.di

import com.example.androidmovielist.data.MoviesListRepository
import com.example.androidmovielist.data.api.MoviesService

object AppResolver {

    private val service = MoviesService.getClient().create(MoviesService::class.java)

    fun provideMoviesRepository() = MoviesListRepository(service)

}