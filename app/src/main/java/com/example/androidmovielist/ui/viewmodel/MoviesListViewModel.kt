package com.example.androidmovielist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.androidmovielist.MoviesConfiguration
import com.example.androidmovielist.data.MoviesListRepository
import com.example.androidmovielist.data.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val repository: MoviesRepository, private val repositoryList: MoviesListRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var mutableMovieList = MutableLiveData<PagedList<MoviesRowViewModel>>()

    val movieList: LiveData<PagedList<MoviesRowViewModel>> by lazy {
        mutableMovieList
    }

    fun loadMoviesList() {
        compositeDisposable.add(repository.loadTopMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result ->
                result.results.map { item ->
                    MoviesRowViewModel(getImagePath(item.backdrop_path), item.title, item.vote_average.toString(), false)
                }
            }
            .subscribe { item ->
                mutableMovieList.value = item

            }
        )
    }

    private fun getImagePath(backdropPath: String): String {
        return MoviesConfiguration.baseUrl + "w500" + backdropPath
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

    fun loadConfiguration() {
        compositeDisposable.add(repositoryList.getConfiguration()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { item ->
                MoviesConfiguration.baseUrl = item.images.secure_base_url
                loadMoviesList()
                loadDetailsFirstTopRatedMovie()
            })
    }
}