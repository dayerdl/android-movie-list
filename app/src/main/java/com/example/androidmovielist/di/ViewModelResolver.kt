package com.example.androidmovielist.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.androidmovielist.data.MoviesListRepository
import com.example.androidmovielist.ui.viewmodel.MoviesListViewModel

object ViewModelResolver {

    fun provideViewModel(owner: ViewModelStoreOwner, factory: MoviesListViewModelFactory): MoviesListViewModel =
        ViewModelProvider(owner, factory).get(MoviesListViewModel::class.java)

    fun provideViewModelFactory(repository: MoviesListRepository) = MoviesListViewModelFactory(repository)

}