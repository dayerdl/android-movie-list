package com.example.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.movies.IMoviesRepository
import com.example.injector.Injector
import com.example.injector.InjectorResolver

class MovieDetailActivity : AppCompatActivity(), InjectorResolver {

    lateinit var moviesRepository: IMoviesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resolveInjector().inject(this)
        setContentView(R.layout.movie_detail)

        moviesRepository.loadMovieDetails(1)
    }

    override fun resolveInjector(): Injector {
        return application as Injector
    }
}

