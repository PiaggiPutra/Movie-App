package id.android.codebase.data.model.response

import id.android.codebase.data.model.MovieItem

data class ListMovieResponse(
    var page : Int?,
    var results : List<MovieItem>
)