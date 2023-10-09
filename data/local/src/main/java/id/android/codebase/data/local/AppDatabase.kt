package id.android.codebase.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.android.codebase.data.local.converter.Converters
import id.android.codebase.data.local.dao.FavoriteMovieDao
import id.android.codebase.data.model.MovieItemDTO

@Database(entities = [MovieItemDTO::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    // DAO
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object {

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "App.db")
                .build()
    }
}
