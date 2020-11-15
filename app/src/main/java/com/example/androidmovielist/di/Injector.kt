package com.example.androidmovielist.di

import android.app.Activity
import androidx.lifecycle.ViewModelStoreOwner
import com.example.androidmovielist.ui.viewmodel.MoviesListViewModel

object Injector {

    private val repository by lazy {
        AppResolver.provideMoviesRepository()
    }

    private val detailMoviesListFactory by lazy {
        ViewModelResolver.provideViewModelFactory(repository)
    }

    fun moviesViewModel(activity: Activity): MoviesListViewModel {
        return ViewModelResolver.provideViewModel(activity as ViewModelStoreOwner, detailMoviesListFactory)
    }
}