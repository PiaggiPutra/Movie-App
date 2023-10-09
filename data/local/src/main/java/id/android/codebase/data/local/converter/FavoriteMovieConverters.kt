package id.android.codebase.data.local.converter

import id.android.codebase.data.model.MovieItem
import id.android.codebase.data.model.MovieItemDTO

class FavoriteMovieConverters {
    fun movieItemToMovieDTO(movie: MovieItem): MovieItemDTO {
        return MovieItemDTO(
            movie.id,
            movie.originalTitle,
            movie.posterPath,
            movie.voteAverage.toString()
        )
    }

    fun movieDTOToMovieItem(roomMovie: MovieItemDTO) : MovieItem {
        return MovieItem(
            roomMovie.id,
            roomMovie.originalTitle,
            roomMovie.posterPath,
            roomMovie.voteAverage.toDouble()
        )
    }
}