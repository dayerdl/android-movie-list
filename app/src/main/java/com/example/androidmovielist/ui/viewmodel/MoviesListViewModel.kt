package com.example.androidmovielist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidmovielist.data.MoviesRepository
import com.example.androidmovielist.data.database.LocalMovie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val repository: MoviesRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var mutableMovieList = MutableLiveData<List<MoviesRowViewModel>>()
    private var mutableFavouriteToggle = MutableLiveData<Boolean>(false)

    val movieList: LiveData<List<MoviesRowViewModel>> by lazy { mutableMovieList }
    val favouriteToggle: LiveData<Boolean> = mutableFavouriteToggle

    fun loadMoviesList() {
        compositeDisposable.add(repository.loadTopMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result ->
                result.results.map { item ->
                    MoviesRowViewModel(item.backdrop_path, item.title, item.vote_average.toString(), false)
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

    fun onFavouritePressed() {
        mutableFavouriteToggle.postValue(!(mutableFavouriteToggle.value as Boolean))
    }

    fun save(item: MoviesRowViewModel) {
        val movie = LocalMovie(Math.random().toInt(), item.title, "dummy")
        repository.saveMovie(movie)
    }

    fun loadFavourites() {
        compositeDisposable.add(repository.loadUserSavedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { item ->
                println("ZAXA----> $item")
            }
        )

    }

}