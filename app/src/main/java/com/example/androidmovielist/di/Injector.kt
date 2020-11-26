package com.example.androidmovielist.di

import android.app.Activity
import androidx.lifecycle.ViewModelStoreOwner
import com.example.androidmovielist.data.api.MoviesService
import com.example.androidmovielist.ui.viewmodel.MoviesListViewModel

object Injector {

    private val network = NetworkResolver.provideNetwork()

    private val movieSource by lazy {
        AppResolver.provideMovieSource(network)
    }

    val repository by lazy {
        AppResolver.provideMoviesRepository(movieSource)
    }

    private val detailMoviesListFactory by lazy {
        ViewModelResolver.provideViewModelFactory(repository)
    }

    fun moviesViewModel(activity: Activity): MoviesListViewModel {
        return ViewModelResolver.provideViewModel(activity as ViewModelStoreOwner, detailMoviesListFactory)
    }
}