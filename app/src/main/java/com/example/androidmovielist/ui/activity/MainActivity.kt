package com.example.androidmovielist.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmovielist.R
import com.example.androidmovielist.di.Injector
import com.example.androidmovielist.ui.MovieListAdapter
import com.example.androidmovielist.ui.MovieRowViewHolderCallBack
import com.example.androidmovielist.ui.viewmodel.MoviesRowViewModel
import com.example.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MovieRowViewHolderCallBack, SwipeRefreshLayout.OnRefreshListener,
    Toolbar.OnMenuItemClickListener {

    @Inject lateinit var viewModel : MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swiperefresh.setOnRefreshListener(this)

        val viewModel = Injector.moviesViewModel(this)

        listOfMovies.layoutManager = LinearLayoutManager(this.baseContext, LinearLayoutManager.VERTICAL, false)
        viewModel.movieList.observe(this, Observer {
            listOfMovies.adapter = MovieListAdapter(it, this)
            (listOfMovies.adapter as MovieListAdapter).notifyDataSetChanged()
            progressBar.hide()
            swiperefresh.isRefreshing = false
        })

        progressBar.display()
        viewModel.loadMoviesList()
        viewModel.loadDetailsFirstTopRatedMovie()

        setupToolBar()

    }

    private fun setupToolBar() {
        toolbar.title = getString(R.string.title_top_rated_list_movies)
        toolbar.setOnMenuItemClickListener(this)
    }

    override fun clickOnFavouriteItem(item: MoviesRowViewModel) {
        startActivity(Intent(this, MovieDetailActivity::class.java))
    }

    override fun onRefresh() {
        swiperefresh.display()
        viewModel.loadMoviesList()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_favorite -> {
                Toast.makeText(baseContext, "Favs", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}

fun View.display() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

