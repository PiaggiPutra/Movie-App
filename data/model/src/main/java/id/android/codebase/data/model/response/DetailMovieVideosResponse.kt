package id.android.codebase.data.model.response

import com.google.gson.annotations.SerializedName

data class DetailMovieVideosResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("results")
    var results: List<Result>
) {
    data class Result(
        @SerializedName("id")
        var id: String,
        @SerializedName("key")
        var key: String,
        @SerializedName("name")
        var name: String,
    )
}