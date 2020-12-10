package com.example.androidmovielist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidmovielist.R
import com.example.androidmovielist.ui.viewmodel.MoviesRowViewModel
import kotlinx.android.synthetic.main.movie_row.view.*

interface MovieRowViewHolderCallBack {
    fun clickOnFavouriteItem(item: MoviesRowViewModel)
}

class MovieListAdapter(
    private var items: List<MoviesRowViewModel>,
    val callback: MovieRowViewHolderCallBack
) : RecyclerView.Adapter<MovieRowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.movie_row, null)
        return MovieRowViewHolder(row)
    }

    override fun onBindViewHolder(holder: MovieRowViewHolder, position: Int) {
        holder.bind(items[position], callback)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}

class MovieRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: MoviesRowViewModel, callback: MovieRowViewHolderCallBack) {
        itemView.title_movie.text = item.title
        itemView.rating.text = item.rating
        Glide
            .with(itemView.context)
            .load(item.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.movie_placeholder)
            .into(itemView.image_movie);

        if (item.isFavourite) {
            itemView.fav_icon.setImageResource(R.drawable.ic_love_filled)
        } else {
            itemView.fav_icon.setImageResource(R.drawable.ic_love)
        }
        itemView.fav_icon.setOnClickListener {
            callback.clickOnFavouriteItem(item)
        }
    }
}