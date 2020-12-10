package com.example.androidmovielist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidmovielist.data.MoviesListRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val repository: MoviesListRepository) :
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
                    MoviesRowViewModel(item., item.title, item.vote_average.toString(), false)
                }
            }
            .subscribe { item ->
                mutableMovieList.value = item
            }
        )
    }

    fun loadDetailsFirstTopRatedMovie(){
        compositeDisposable.add(repository.loadTopMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { results -> results.results.first()}
            .flatMap { repository.loadMovieDetails(it.id) }
            .subscribe { item ->
                println("ZAXA----> $item")
            }
        )

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}