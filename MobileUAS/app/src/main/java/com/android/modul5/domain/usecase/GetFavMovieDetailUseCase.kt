package com.android.modul5.domain.usecase

import com.android.modul5.domain.model.MovieDetail
import com.android.modul5.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavMovieDetailUseCase(private val repository: MovieRepository) {
    operator fun invoke(movieId: Int): Flow<MovieDetail?> {
        return repository.getFavMovieDetail(movieId)
    }
}