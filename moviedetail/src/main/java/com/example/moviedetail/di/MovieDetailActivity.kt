package com.example.moviedetail.di

import android.os.Bundle
import com.example.domain.movies.IMoviesRepository
import com.example.moviedetail.R
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : DaggerAppCompatActivity() {

    @Inject lateinit var repository: IMoviesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.movie_detail)

        button.setOnClickListener {
            CompositeDisposable().add(repository.loadTopMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { results -> results.results.first() }
                .flatMap { repository.loadMovieDetails(it.id) }
                .subscribe { item ->
                    println("ZAXA----> $item")
                }
            )
        }

    }

}