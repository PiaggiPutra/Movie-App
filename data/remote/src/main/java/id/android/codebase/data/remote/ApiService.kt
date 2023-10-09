package id.android.codebase.data.remote

import id.android.codebase.data.model.response.DetailMovieResponse
import id.android.codebase.data.model.response.DetailMovieVideosResponse
import id.android.codebase.data.model.response.GenresResponse
import id.android.codebase.data.model.response.ListMovieResponse
import id.android.codebase.data.model.response.ListReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getListGenre() : Response<GenresResponse>
    @GET("discover/movie")
    suspend fun getListMovie(@Query("with_genres") genreId: Int, @Query("page") page :Int) : Response<ListMovieResponse>
    @GET("movie/{id}")
    suspend fun getDetailMovie(@Path("id") movieId:Int) : Response<DetailMovieResponse>
    @GET("movie/{id}/videos")
    suspend fun getDetailMovieVideos(@Path("id") movieId:Int) : Response<DetailMovieVideosResponse>
    @GET("movie/{id}/reviews")
    suspend fun getReviewMovie(@Path("id") movieId:Int, @Query("page") page :Int) : Response<ListReviewResponse>
}