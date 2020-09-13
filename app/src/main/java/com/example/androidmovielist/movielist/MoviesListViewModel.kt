package com.example.androidmovielist.movielist

import com.example.androidmovielist.data.MoviesListRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesListViewModel constructor(private val repository: MoviesListRepository = MoviesListRepository()) {

    fun loadMoviesList() {
        //public final Disposable subscribe(final Consumer<? super T> onSuccess, final Consumer<? super Throwable> onError) {
        val disposable = repository.loadMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .map { item -> item.results.size }
            .subscribe { item ->  println("Total size is $item")}
    }

}