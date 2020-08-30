package com.example.androidmovielist.api

import com.example.androidmovielist.model.TopRated
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesService {

    @GET("top_rated?api_key=07473a01a734d6aa462ef4b401276805&language=en-US")
    fun getTopRatedMovies(@Query("page") page: Int): Single<TopRated>

    companion object {
        var BASE_URL =
            "https://api.themoviedb.org/3/movie/"
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }

}