package com.example.androidmovielist.data.api

import com.example.androidmovielist.BuildConfig
import com.example.androidmovielist.data.model.Configuration
import com.example.androidmovielist.data.model.Movie
import com.example.androidmovielist.data.model.TopRatedResults
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesServiceApi {

    @GET("configuration")
    fun getConfiguration(
        @Query("api_key") apiKey: String = BuildConfig.MoviesApiKey
    ): Observable<Configuration>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.MoviesApiKey
    ): Observable<TopRatedResults>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.MoviesApiKey
    ): Observable<Movie>

}