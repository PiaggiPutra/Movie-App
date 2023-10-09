package id.android.codebase.data.repository

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
}

class MovieRepositoryImpl(private val datasource: MovieDataSource): MovieRepository {

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
}