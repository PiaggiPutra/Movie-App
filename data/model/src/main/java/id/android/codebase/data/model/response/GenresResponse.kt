package id.android.codebase.data.model.response

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    var genres: List<Genre>
) {
    data class Genre(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String
    )
}