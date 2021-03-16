package com.example.androidmovielist.di

import com.example.androidmovielist.MoviesApplication
import com.example.androidmovielist.data.MoviesCache
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
    fun provideMovieRepository(service: MoviesApiManager, cache: MoviesCache): IMoviesRepository {
        return MoviesRepository(service, cache)
    }

    @Provides
    fun provideCache(application: MoviesApplication): MoviesCache {
        return MoviesCache(application)
    }

}