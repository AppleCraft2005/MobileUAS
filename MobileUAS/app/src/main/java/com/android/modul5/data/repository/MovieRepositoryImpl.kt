package com.android.modul5.data.repository

import com.android.modul5.data.local.FavoriteMovieDAO
import com.android.modul5.data.local.entity.MovieEntity
import com.android.modul5.data.mapper.toDetailMovie
import com.android.modul5.data.mapper.toMovie
import com.android.modul5.data.mapper.toMovieDetail
import com.android.modul5.data.mapper.toMovieEntity
import com.android.modul5.data.remote.RetrofitClient
import com.android.modul5.data.remote.api.TMDBAPI
import com.android.modul5.data.remote.dto.MovieDTO
import com.android.modul5.domain.model.Movie
import com.android.modul5.domain.model.MovieDetail
import com.android.modul5.domain.repository.MovieRepository
import com.android.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import java.io.IOException

class MovieRepositoryImpl(
    private val DAO: FavoriteMovieDAO,
    private val API: TMDBAPI
): MovieRepository {
    private val API_KEY = RetrofitClient.APIKEY

//    override fun getFavoriteMovies(): Flow<List<Movie>> {
//        return DAO.getFavoriteMovies().map { entityList ->
//            entityList.map { entity ->
//                entity.toMovie()
//            }
//        }
//    }

        override fun getFavoriteMovies(): Flow<List<MovieDetail>> {
        return DAO.getFavoriteMovies().map { entityList ->
            entityList.map { entity ->
                entity.toMovieDetail()
            }
        }
    }

    override fun getFavMovieDetail(movieId: Int): Flow<MovieDetail?> {
        return DAO.getFavMovieDetail(movieId).map { entity ->
            entity?.toMovieDetail()
        }
    }

    override suspend fun addFavMovie(movie: MovieDetail) {
        DAO.addMovieToFav(movie.toMovieEntity())
    }

    override suspend fun removeFavMovie(movieId: Int) {
        DAO.removeMovieFromFav(movieId)
    }

    override suspend fun isMovieFav(movieId: Int): Boolean {
        return DAO.checkMovie(movieId) > 0
    }

//    override suspend fun getMovieDetail(movieId: Int): MovieDetail? {
//        val response = API.getMovieDetailbyID(movieId, API_KEY)
//        if (response.isSuccessful) {
//            return response.body()?.toDetailMovie()
//        }
//        else {
//            // berikan error yang berkaitan dengan API
//            throw Exception("API Error: ${response.code()} - ${response.message()}")
//        }
//    }
//
//    override suspend fun searchMovies(query: String): List<Movie> {
//        val response = API.getMoviesbySearch( API_KEY, query,)
//        if (response.isSuccessful) {
//            // kembalikan list kosong jika hasilnya null
//            val MovieDTOList = response.body()?.results ?: emptyList()
//            return MovieDTOList.map { it.toMovie() }
//        }
//        else {
//            // berikan error yang berkaitan dengan API
//            throw Exception("API Error: ${response.code()} - ${response.message()}")
//        }
//    }
//
//
//    override suspend fun getPopularMovies(): List<Movie> {
//        val response = API.getPopularMovies(API_KEY)
//        if (response.isSuccessful) {
//            // kembalikan list kosong jika hasilnya null
//            val MovieDTOList = response.body()?.results ?: emptyList()
//            return MovieDTOList.map { it.toMovie() }
//        }
//        else {
//            // berikan error yang berkaitan dengan API
//            throw Exception("API Error: ${response.code()} - ${response.message()}")
//        }
//    }
//
//    override suspend fun getAnimeMovies(): List<Movie> {
//        val response = API.getAnimeMovies(API_KEY)
//        if (response.isSuccessful) {
//            // kembalikan list kosong jika hasilnya null
//            val MovieDTOList = response.body()?.results ?: emptyList()
//            return MovieDTOList.map { it.toMovie() }
//        }
//        else {
//            // berikan error yang berkaitan dengan API
//            throw Exception("API Error: ${response.code()} - ${response.message()}")
//        }
//    }
//
//    override suspend fun getIndonesiaMovies(): List<Movie> {
//        val response = API.getIndonesiaMovies(API_KEY)
//        if (response.isSuccessful) {
//            // kembalikan list kosong jika hasilnya null
//            val MovieDTOList = response.body()?.results ?: emptyList()
//            return MovieDTOList.map { it.toMovie() }
//        }
//        else {
//            // berikan error yang berkaitan dengan API
//            throw Exception("API Error: ${response.code()} - ${response.message()}")
//        }
//    }



    override suspend fun getMovieDetail(movieId: Int): MovieDetail? {
        return try {
            val response = API.getMovieDetailbyID(movieId, API_KEY)
            if (response.isSuccessful) {
                response.body()?.toDetailMovie()
            }
            else { null }
        }
        catch (err: IOException) { null }
        catch (err: Exception) { null }
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return try {
            val response = API.getMoviesbySearch( API_KEY, query,)
            if (response.isSuccessful) {
                // kembalikan list kosong jika hasilnya null
                val MovieDTOList = response.body()?.results ?: emptyList()
                return MovieDTOList.map { it.toMovie() }
            }
            else {
                emptyList()
            }
        }
        catch (err: Exception) { emptyList() }
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return try {
            val response = API.getPopularMovies(API_KEY)
            if (response.isSuccessful) {
                // kembalikan list kosong jika hasilnya null
                val MovieDTOList = response.body()?.results ?: emptyList()
                return MovieDTOList.map { it.toMovie() }
            }
            else {
                emptyList()
            }
        }
        catch (err: Exception) { emptyList() }
    }

    override suspend fun getAnimeMovies(): List<Movie> {
        return try {
            val response = API.getAnimeMovies(API_KEY)
            if (response.isSuccessful) {
                // kembalikan list kosong jika hasilnya null
                val MovieDTOList = response.body()?.results ?: emptyList()
                return MovieDTOList.map { it.toMovie() }
            }
            else { emptyList() }
        }
        catch (err: Exception) { emptyList() }
    }

    override suspend fun getIndonesiaMovies(): List<Movie> {
        return try {
            val response = API.getIndonesiaMovies(API_KEY)
            if (response.isSuccessful) {
                // kembalikan list kosong jika hasilnya null
                val MovieDTOList = response.body()?.results ?: emptyList()
                return MovieDTOList.map { it.toMovie() }
            }
            else { emptyList() }
        }
        catch (err: Exception) { emptyList() }
    }

//    override suspend fun getMovieDetail(movieId: Int): Resource<MovieDetail?> {
//        return try {
//            val response = API.getMovieDetailbyID(movieId, API_KEY)
//            if (response.isSuccessful) {
//                Resource.Success(response.body()?.toDetailMovie())
//            }
//            else { Resource.Error("Error: ${response.code()}") }
//        }
//        catch (err: IOException) { Resource.Error("No Internet Connection") }
//        catch (err: Exception) { Resource.Error("Unexpected Error") }
//    }
//
//    override suspend fun searchMovies(query: String): Resource<List<Movie>> {
//        return try {
//            val response = API.getMoviesbySearch( API_KEY, query,)
//            if (response.isSuccessful) {
//                // kembalikan list kosong jika hasilnya null
//                val MovieDTOList = response.body()?.results ?: emptyList()
//                Resource.Success(MovieDTOList.map { it.toMovie() })
//            }
//            else { Resource.Error("Error: ${response.code()}") }
//        }
//        catch (err: IOException) { Resource.Error("No Internet Connection") }
//        catch (err: Exception) { Resource.Error("Unexpected Error") }
//    }
//
//    override suspend fun getPopularMovies(): Resource<List<Movie>> {
//        return try {
//            val response = API.getPopularMovies(API_KEY)
//            if (response.isSuccessful) {
//                // kembalikan list kosong jika hasilnya null
//                val MovieDTOList = response.body()?.results ?: emptyList()
//                Resource.Success(MovieDTOList.map { it.toMovie() })
//            }
//            else { Resource.Error("Error: ${response.code()}") }
//        }
//        catch (err: IOException) { Resource.Error("No Internet Connection") }
//        catch (err: Exception) { Resource.Error("Unexpected Error") }
//    }
//
//    override suspend fun getAnimeMovies(): Resource<List<Movie>> {
//        return try {
//            val response = API.getAnimeMovies(API_KEY)
//            if (response.isSuccessful) {
//                // kembalikan list kosong jika hasilnya null
//                val MovieDTOList = response.body()?.results ?: emptyList()
//                Resource.Success(MovieDTOList.map { it.toMovie() })
//            }
//            else { Resource.Error("Error: ${response.code()}") }
//        }
//        catch (err: IOException) { Resource.Error("No Internet Connection") }
//        catch (err: Exception) { Resource.Error("Unexpected Error") }
//    }
//
//    override suspend fun getIndonesiaMovies(): Resource<List<Movie>> {
//        return try {
//            val response = API.getIndonesiaMovies(API_KEY)
//            if (response.isSuccessful) {
//                // kembalikan list kosong jika hasilnya null
//                val MovieDTOList = response.body()?.results ?: emptyList()
//                Resource.Success(MovieDTOList.map { it.toMovie() })
//            }
//            else { Resource.Error("Error: ${response.code()}") }
//        }
//        catch (err: IOException) { Resource.Error("No Internet Connection") }
//        catch (err: Exception) { Resource.Error("Unexpected Error") }
//    }
}



