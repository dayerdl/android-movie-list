package com.example.androidmovielist.ui.viewmodel

import androidx.lifecycle.*
import com.example.androidmovielist.data.MoviesRepository
import com.example.androidmovielist.data.database.LocalMovie
import com.example.domain.movies.ITopRatedResults
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
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
        compositeDisposable.add(
//            Observable.zip(repository.loadTopMovies().toFlowable(),
//                repository.loadUserSavedMovies(),
//                BiFunction { t1:ITopRatedResults, t2:ITopRatedResults -> return@BiFunction setFavourites(t1,t2) }
//            )
            repository.loadTopMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result ->
                result.results.map { item ->
                    MoviesRowViewModel(
                        item.id,
                        item.backdrop_path,
                        item.title,
                        item.vote_average.toString(),
                        false
                    )
                }
            }
            .subscribe { item ->
                mutableMovieList.value = item
            }
        )
    }

    private fun setFavourites(t1: ITopRatedResults, t2: ITopRatedResults): ITopRatedResults {
        return t1
    }

    fun loadDetailsFirstTopRatedMovie() {
        compositeDisposable.add(repository.loadTopMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { results -> results.results.first() }
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
        val movie = LocalMovie(id = item.id, title = item.title)
        repository.saveMovie(movie)
        item.isFavourite = true

    }

    fun loadFavourites(): Single<ArrayList<MoviesRowViewModel>> {
        return repository.loadUserSavedMovies()
            .map {
                val list = ArrayList<MoviesRowViewModel>()
                for(movie in it) {
                    val movie = MoviesRowViewModel(
                        id = movie.id,
                        imageUrl = "",
                        title = "",
                        rating = movie.vote_average.toString(),
                        isFavourite = true
                    )
                    list.add(movie)
                }
                list
        }
    }

}