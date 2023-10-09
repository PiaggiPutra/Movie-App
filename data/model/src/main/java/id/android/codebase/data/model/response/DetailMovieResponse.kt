package id.android.codebase.data.model.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("vote_average")
    var voteAverage: Double
)