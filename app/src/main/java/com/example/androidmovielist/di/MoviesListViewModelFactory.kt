package com.example.androidmovielist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidmovielist.data.MoviesListRepository
import com.example.androidmovielist.ui.viewmodel.MoviesListViewModel

class MoviesListViewModelFactory(private val repository: MoviesListRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesListViewModel(repository) as T
    }

}