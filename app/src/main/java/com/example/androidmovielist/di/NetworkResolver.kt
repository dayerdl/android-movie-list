package com.example.androidmovielist.di

import com.example.androidmovielist.data.api.MoviesService

object NetworkResolver {

    fun provideNetwork(): MoviesService.Companion {
        return MoviesService
    }

}
