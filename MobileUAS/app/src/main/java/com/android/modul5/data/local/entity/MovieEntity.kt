package com.android.modul5.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val popularity: Double = 0.0,
    val originalLanguage: String,
    val originCountry: List<String> = emptyList(),
    val overview: String,
    val posterPath: String?,
    val releaseDate: String = "-",
    val title: String,
    val voteAverage: Double = 0.0,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = ""
)