package com.example.androidmovielist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidmovielist.data.MoviesListRepository

class MoviesListViewModelFactory(private val repository: MoviesListRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesListViewModel(repository) as T
    }

}