package com.example.androidmovielist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmovielist.R
import com.example.androidmovielist.ui.viewmodel.MoviesListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = MoviesListViewModel()

        button.setOnClickListener { viewModel.loadMoviesList() }

        listOfMovies.adapter =
    }
}