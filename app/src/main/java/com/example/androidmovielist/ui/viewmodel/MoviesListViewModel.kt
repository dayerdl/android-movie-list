package com.example.androidmovielist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidmovielist.data.MoviesListRepository
import com.example.androidmovielist.data.api.MoviesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesListViewModel constructor(private val service: MoviesService = MoviesService.getClient().create(MoviesService::class.java),
                                      private val repository: MoviesListRepository = MoviesListRepository(service)) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var mutableMovieList = MutableLiveData<List<MoviesRowViewModel>>()

    val movieList: LiveData<List<MoviesRowViewModel>> by lazy {
        mutableMovieList
    }

    fun loadMoviesList() {
        compositeDisposable.add(repository.loadTopMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result ->
                result.results.map { item ->
                    MoviesRowViewModel("", item.title, item.vote_average.toString(), true)
                }
            }
            .subscribe { item ->
                mutableMovieList.value = item
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}