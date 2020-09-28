package com.example.androidmovielist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidmovielist.data.MoviesListRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesListViewModel constructor(private val repository: MoviesListRepository = MoviesListRepository()) :
    ViewModel() {

    var mutableMovieList = MutableLiveData<List<MoviesRowViewModel>>()

    val movieList: LiveData<List<MoviesRowViewModel>> by lazy {
        mutableMovieList
    }

    fun loadMoviesList() {
        repository.loadTopMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result ->
                result.results.map { item ->
                    MoviesRowViewModel("", item.title, item.vote_average.toString())
                }
            }
            .subscribe { item ->
                println("Total size is $item")
                mutableMovieList.value = item
            }
    }
}