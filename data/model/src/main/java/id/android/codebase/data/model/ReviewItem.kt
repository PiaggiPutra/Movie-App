package id.android.codebase.data.model

import com.google.gson.annotations.SerializedName

data class ReviewItem(
    @SerializedName("author")
    var author: String,
    @SerializedName("content")
    var content: String,
    @SerializedName("id")
    var id: String
)