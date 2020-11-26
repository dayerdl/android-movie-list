package com.example.androidmovielist.data.model

import com.example.domain.movies.ITopRatedResults
import com.google.gson.annotations.SerializedName

data class TopRatedResults(
    @SerializedName("page") override val page : Int,
    @SerializedName("total_results") override val total_results : Int,
    @SerializedName("total_pages") override val total_pages : Int,
    @SerializedName("results") override val results : List<Movie>
): ITopRatedResults