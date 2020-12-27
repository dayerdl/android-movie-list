package com.example.domain.movies

import io.reactivex.Single


interface IMoviesRepository {
    fun loadTopMovies(): Single<ITopRatedResults>
    fun loadMovieDetails(movieId: Int): Single<IMovie>
}


interface ITopRatedResults {
    val page : Int
    val total_results : Int
    val total_pages : Int
    val results : List<IMovie>
}

interface IMovie {
    val popularity: Double
    val vote_count: Int
    val video: Boolean
    val poster_path: String
    val id: Int
    val adult: Boolean
    val backdrop_path: String
    val original_language: String
    val original_title: String
    val genre_ids: List<Int>
    val title: String
    val vote_average: Double
    val overview: String
    val release_date: String
}