package com.example.androidmovielist.data.model

import com.example.domain.movies.IMovie
import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("popularity") override val popularity : Double,
    @SerializedName("vote_count") override val vote_count : Int,
    @SerializedName("video") override val video : Boolean,
    @SerializedName("poster_path") override val poster_path : String,
    @SerializedName("id") override val id : Int,
    @SerializedName("adult") override val adult : Boolean,
    @SerializedName("backdrop_path") override val backdrop_path : String,
    @SerializedName("original_language") override val original_language : String,
    @SerializedName("original_title") override val original_title : String,
    @SerializedName("genre_ids") override val genre_ids : List<Int>,
    @SerializedName("title") override val title : String,
    @SerializedName("vote_average") override val vote_average : Double,
    @SerializedName("overview") override val overview : String,
    @SerializedName("release_date") override val release_date : String
): IMovie