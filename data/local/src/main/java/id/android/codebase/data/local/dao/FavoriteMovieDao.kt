package id.android.codebase.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import id.android.codebase.data.model.MovieItemDTO

@Dao
abstract class FavoriteMovieDao: BaseDao<MovieItemDTO>() {

    @Query("SELECT * FROM FAVORITE_MOVIE")
    abstract fun getAllMovie(): List<MovieItemDTO>

    fun saveMovie(data: MovieItemDTO) {
        insert(data)
    }

    @Query("DELETE FROM FAVORITE_MOVIE WHERE originalTitle = :name")
    abstract fun deleteMoviebyName(name: String)

    @Query("SELECT EXISTS(SELECT * FROM favorite_movie WHERE id = :id)")
    abstract fun isMovieFavorite(id:Int): Boolean
}