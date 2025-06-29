package com.android.modul5.data.mapper

import com.android.modul5.data.local.entity.MovieEntity
import com.android.modul5.data.remote.dto.MovieDTO
import com.android.modul5.data.remote.dto.MovieDetailDTO
import com.android.modul5.domain.model.Movie
import com.android.modul5.domain.model.MovieDetail

fun MovieDTO.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        originalLanguage = this.originalLanguage,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        popularity = this.popularity,
    )
}

fun MovieDetailDTO.toDetailMovie(): MovieDetail {
    return MovieDetail(
        id = this.id,
        title = this.title,
        originalLanguage = this.originalLanguage,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        popularity = this.popularity,
        originCountry = this.originCountry,
        voteAverage = this.voteAverage,
        status = this.status,
        tagline = this.tagline,
        runtime = this.runtime
    )
}

//fun MovieEntity.toMovie(): Movie {
//    return Movie(
//        id = this.id,
//        title = this.title,
//        originalLanguage = this.originalLanguage,
//        overview = this.overview,
//        posterPath = this.posterPath,
//        releaseDate = this.releaseDate,
//        popularity = this.popularity,
//    )
//}

fun MovieEntity.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = this.id,
        title = this.title,
        originalLanguage = this.originalLanguage,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        popularity = this.popularity,
        originCountry = this.originCountry,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        voteAverage = this.voteAverage,
    )
}

fun MovieDetail.toMovieEntity(): MovieEntity{
    return MovieEntity(
        id = this.id,
        title = this.title,
        originalLanguage = this.originalLanguage,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        popularity = this.popularity,
        originCountry = this.originCountry,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        voteAverage = this.voteAverage,
    )
}