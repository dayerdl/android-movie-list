package com.example.androidmovielist.di

import com.example.androidmovielist.data.MoviesListRepository
import com.example.androidmovielist.data.MoviesRepository
import com.example.androidmovielist.data.api.MoviesApiManager
import com.example.domain.movies.IMoviesRepository
import dagger.Module
import dagger.Provides

@Module
class MoviesModuleRepository {

    @Provides
    fun provideApiManager(): MoviesApiManager {
        return MoviesApiManager()
    }

    @Provides
    fun provideMovieListRepository(service: MoviesApiManager) : MoviesListRepository {
        return MoviesListRepository(service)
    }

    @Provides
    fun provideMovieRepository(service: MoviesApiManager) : IMoviesRepository {
        return MoviesRepository(service)
    }
}