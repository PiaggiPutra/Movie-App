package id.android.codebase.data.model.response

import com.google.gson.annotations.SerializedName
import id.android.codebase.data.model.ReviewItem

data class ListReviewResponse(
    @SerializedName("page")
    var page : Int?,
    @SerializedName("results")
    var results : List<ReviewItem>,
    @SerializedName("total_pages")
    var totalPages: Int?
)