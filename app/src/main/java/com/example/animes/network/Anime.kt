package com.example.animes.network

data class Anime(
    val title: String,
    val original_title: String,
    val original_title_romanised: String,
    val image: String,
    val description: String,
    val director: String,
    val producer: String,
    val release_date: String,
    val rt_score: String
)
