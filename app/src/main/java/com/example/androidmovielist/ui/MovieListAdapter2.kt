package com.example.androidmovielist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.androidmovielist.R
import com.example.androidmovielist.ui.viewmodel.MoviesRowViewModel

class MovieListAdapter2(val callback: MovieRowViewHolderCallBack) :
    PagedListAdapter<MoviesRowViewModel, MovieRowViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesRowViewModel>() {
            override fun areItemsTheSame(oldItem: MoviesRowViewModel, newItem: MoviesRowViewModel) =
                oldItem.imageUrl == newItem.imageUrl

            override fun areContentsTheSame(
                oldItem: MoviesRowViewModel, newItem: MoviesRowViewModel
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.movie_row, null)
        return MovieRowViewHolder(row)
    }

    override fun onBindViewHolder(holder: MovieRowViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, callback)
        }
    }

}