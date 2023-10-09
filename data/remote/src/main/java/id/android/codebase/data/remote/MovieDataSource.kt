package id.android.codebase.data.remote

import id.android.codebase.data.model.response.DetailMovieResponse
import id.android.codebase.data.model.response.DetailMovieVideosResponse
import id.android.codebase.data.model.response.GenresResponse
import id.android.codebase.data.model.response.ListMovieResponse
import id.android.codebase.data.model.response.ListReviewResponse
import retrofit2.Response

class MovieDataSource(private val  apiService: ApiService) {

    suspend fun getListGenre() : Response<GenresResponse> {
        return apiService.getListGenre()
    }

    suspend fun getListMovie(genreId:Int, page :Int) : Response<ListMovieResponse> {
        return apiService.getListMovie(genreId, page)
    }

    suspend fun getDetailMovie(id : Int) : Response<DetailMovieResponse> {
        return apiService.getDetailMovie(id)
    }

    suspend fun getDetailMovieVideos(id : Int) : Response<DetailMovieVideosResponse> {
        return apiService.getDetailMovieVideos(id)
    }

    suspend fun getReviewMovie(id:Int, page:Int) : Response<ListReviewResponse> {
        return apiService.getReviewMovie(id, page)
    }
}