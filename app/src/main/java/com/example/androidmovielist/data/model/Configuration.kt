package com.example.androidmovielist.data.model

data class Configuration (
    val images: Images,
    val changeKeys: List<String>
)

data class Images (
    val base_url: String,
    val secure_base_url: String,
    val backdrop_sizes: List<String>,
    val logo_sizes: List<String>,
    val poster_sizes: List<String>,
    val profile_sizes: List<String>,
    val still_sizes: List<String>
)
