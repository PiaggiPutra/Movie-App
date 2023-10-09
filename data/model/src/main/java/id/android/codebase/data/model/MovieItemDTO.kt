package id.android.codebase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favorite_movie")
data class MovieItemDTO(
    @PrimaryKey(autoGenerate = true)
    @Expose
    @NotNull
    @SerializedName("id")
    var id: Int,

    @Expose
    @SerializedName("original_title")
    var originalTitle: String,

    @Expose
    @SerializedName("poster_path")
    var posterPath: String,

    @Expose
    @SerializedName("vote_average")
    var voteAverage: String
)