package id.android.codebase.data.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("vote_average")
    var voteAverage: Double
)