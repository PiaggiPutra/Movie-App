package id.android.codebase.data.repository

import id.android.codebase.data.local.converter.FavoriteMovieConverters
import id.android.codebase.data.local.dao.FavoriteMovieDao
import id.android.codebase.data.model.MovieItem
import id.android.codebase.data.model.response.DetailMovieResponse
import id.android.codebase.data.model.response.DetailMovieVideosResponse
import id.android.codebase.data.model.response.GenresResponse
import id.android.codebase.data.model.response.ListMovieResponse
import id.android.codebase.data.model.response.ListReviewResponse
import id.android.codebase.data.remote.MovieDataSource
import id.android.codebase.data.repository.utils.Resource
import id.android.codebase.data.repository.utils.getFlow
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getListMovie(genreId : Int, page: Int): Flow<Resource<ListMovieResponse>>
    suspend fun getDetailMovie(id:Int) : Flow<Resource<DetailMovieResponse>>
    suspend fun getListGenre() : Flow<Resource<GenresResponse>>
    suspend fun getDetailMovieVideos(id:Int) : Flow<Resource<DetailMovieVideosResponse>>
    suspend fun getReviewMovie(id: Int, page: Int): Flow<Resource<ListReviewResponse>>
    suspend fun checkIfFavoriteItemMovie(id:Int) : Boolean
    suspend fun getAllFavoriteMovie() : List<MovieItem>
    suspend fun addMovieFavorite(data : MovieItem)
    suspend fun deleteMovieFavoriteItem(name: String)
}

class MovieRepositoryImpl(private val datasource: MovieDataSource, private val favoriteMovieDao : FavoriteMovieDao): MovieRepository {
    private val favoriteMovieConverter = FavoriteMovieConverters()

    override suspend fun getListMovie(genreId: Int, page: Int): Flow<Resource<ListMovieResponse>> {
        return datasource.getListMovie(genreId, page).getFlow()
    }
    override suspend fun getDetailMovie(id: Int): Flow<Resource<DetailMovieResponse>> {
        return datasource.getDetailMovie(id).getFlow()
    }
    override suspend fun getListGenre(): Flow<Resource<GenresResponse>> {
        return datasource.getListGenre().getFlow()
    }

    override suspend fun getDetailMovieVideos(id: Int): Flow<Resource<DetailMovieVideosResponse>> {
        return datasource.getDetailMovieVideos(id).getFlow()
    }

    override suspend fun getReviewMovie(id: Int, page: Int): Flow<Resource<ListReviewResponse>> {
        return datasource.getReviewMovie(id, page).getFlow()
    }

    override suspend fun checkIfFavoriteItemMovie(id: Int): Boolean {
        return favoriteMovieDao.isMovieFavorite(id)
    }
    override suspend fun getAllFavoriteMovie(): List<MovieItem> {
        return favoriteMovieDao.getAllMovie().map {
            favoriteMovieConverter.movieDTOToMovieItem(it)
        }
    }
    override suspend fun addMovieFavorite(data: MovieItem) {
        return favoriteMovieDao.saveMovie(favoriteMovieConverter.movieItemToMovieDTO(data))
    }
    override suspend fun deleteMovieFavoriteItem(name: String) {
        return favoriteMovieDao.deleteMoviebyName(name)
    }
}