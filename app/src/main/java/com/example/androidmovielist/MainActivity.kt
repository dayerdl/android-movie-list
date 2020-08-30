package com.example.androidmovielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmovielist.api.MoviesService
import com.example.androidmovielist.movielist.MoviesListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = MoviesListViewModel()

        button.setOnClickListener { viewModel.loadMoviesList() }
    }
}