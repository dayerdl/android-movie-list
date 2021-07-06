package com.example.androidmovielist.data.api

import com.example.androidmovielist.data.model.Movie
import com.example.androidmovielist.data.model.TopRatedResults
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "07473a01a734d6aa462ef4b401276805"
    ): Observable<TopRatedResults>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")movieId: Int,
        @Query("api_key") apiKey: String = "07473a01a734d6aa462ef4b401276805"
    ): Observable<Movie>

}