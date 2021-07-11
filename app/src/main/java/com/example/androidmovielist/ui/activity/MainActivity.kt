package com.example.androidmovielist.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.androidmovielist.R
import com.example.androidmovielist.ui.MovieListAdapter
import com.example.androidmovielist.ui.MovieRowViewHolderCallBack
import com.example.androidmovielist.ui.viewmodel.MoviesListViewModel
import com.example.androidmovielist.ui.viewmodel.MoviesRowViewModel
import com.example.moviedetail.di.MovieDetailActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MovieRowViewHolderCallBack,
    SwipeRefreshLayout.OnRefreshListener,
    Toolbar.OnMenuItemClickListener {

    @Inject
    lateinit var viewModel: MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swiperefresh.setOnRefreshListener(this)

        listOfMovies.layoutManager =
            LinearLayoutManager(this.baseContext, LinearLayoutManager.VERTICAL, false)
        viewModel.movieList.observe(this, Observer {
            listOfMovies.adapter = MovieListAdapter(it, this)
            (listOfMovies.adapter as MovieListAdapter).notifyDataSetChanged()
            progressBar.hide()
            swiperefresh.isRefreshing = false
        })

        progressBar.display()
        viewModel.loadMoviesList()
        viewModel.loadDetailsFirstTopRatedMovie()

        viewModel.favouriteToggle.observe(this, Observer {
            if (it == true) {
                toolbar.menu.getItem(0).icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_love_filled)
                viewModel.loadFavourites()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { it ->
                            listOfMovies.adapter = MovieListAdapter(it, this)
                            Toast.makeText(this, "${it.size}", Toast.LENGTH_SHORT).show()
                    }

            } else {
                toolbar.menu.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_love)
                viewModel.movieList.observe(this, Observer { completeList ->
                    listOfMovies.adapter = MovieListAdapter(completeList, this)
                })
            }
        })

        setupToolBar()

    }

    private fun setupToolBar() {
        toolbar.title = getString(R.string.title_top_rated_list_movies)
        toolbar.setOnMenuItemClickListener(this)
    }

    override fun clickOnFavouriteItem(item: MoviesRowViewModel) {
        viewModel.save(item)
        listOfMovies.adapter?.notifyDataSetChanged()
    }

    override fun clickOnMovieItem(item: MoviesRowViewModel) {
        startActivity(Intent(this, MovieDetailActivity::class.java))
    }

    override fun onRefresh() {
        swiperefresh.display()
        viewModel.loadMoviesList()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_favourite -> {
                viewModel.onFavouritePressed()
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

