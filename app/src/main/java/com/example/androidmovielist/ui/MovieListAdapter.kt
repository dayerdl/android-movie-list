package com.example.androidmovielist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmovielist.R
import com.example.androidmovielist.ui.viewmodel.MoviesRowViewModel
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieListAdapter(private var items: List<MoviesRowViewModel>) : RecyclerView.Adapter<MovieRowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.movie_row, null)
        return MovieRowViewHolder(row)
    }

    override fun onBindViewHolder(holder: MovieRowViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}

class MovieRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: MoviesRowViewModel){
        itemView.title_movie.text = item.title
        itemView.rating.text = item.rating
    }
}