package com.example.androidmovielist.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmovielist.MoviesApplication
import com.example.androidmovielist.R
import com.example.androidmovielist.ui.MovieListAdapter
import com.example.androidmovielist.ui.MovieRowViewHolderCallBack
import com.example.androidmovielist.ui.viewmodel.MoviesListViewModel
import com.example.androidmovielist.ui.viewmodel.MoviesRowViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MovieRowViewHolderCallBack {

    @Inject lateinit var viewModel : MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as MoviesApplication).appComponent.inject(this)

        listOfMovies.layoutManager = LinearLayoutManager(this.baseContext, LinearLayoutManager.VERTICAL, false)
        viewModel.movieList.observe(this, Observer {
            listOfMovies.adapter = MovieListAdapter(it, this)
            (listOfMovies.adapter as MovieListAdapter).notifyDataSetChanged()
        })

        load_movies_button.setOnClickListener {
            viewModel.loadMoviesList()
            viewModel.loadDetailsFirstTopRatedMovie()
        }
    }

    override fun clickOnFavouriteItem(item: MoviesRowViewModel) {

    }
}