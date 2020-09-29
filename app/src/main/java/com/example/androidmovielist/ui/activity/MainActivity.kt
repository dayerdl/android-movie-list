package com.example.androidmovielist.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmovielist.R
import com.example.androidmovielist.ui.MovieListAdapter
import com.example.androidmovielist.ui.MovieRowViewHolderCallBack
import com.example.androidmovielist.ui.viewmodel.MoviesListViewModel
import com.example.androidmovielist.ui.viewmodel.MoviesRowViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieRowViewHolderCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MoviesListViewModel by viewModels()

        listOfMovies.layoutManager = LinearLayoutManager(this.baseContext, LinearLayoutManager.VERTICAL, false)
        viewModel.movieList.observe(this, Observer {
            listOfMovies.adapter = MovieListAdapter(it, this)
            (listOfMovies.adapter as MovieListAdapter).notifyDataSetChanged()
        })

        load_movies_button.setOnClickListener { viewModel.loadMoviesList() }

    }

    override fun clickOnFavouriteItem(item: MoviesRowViewModel) {

    }
}