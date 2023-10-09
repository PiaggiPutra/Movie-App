package id.android.codebase.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.android.codebase.common.base.BaseViewModel
import id.android.codebase.common.utils.keys.Keys
import id.android.codebase.data.model.MovieItem
import id.android.codebase.data.model.ReviewItem
import id.android.codebase.data.model.response.DetailMovieResponse
import id.android.codebase.data.model.response.DetailMovieVideosResponse
import id.android.codebase.data.repository.AppDispatchers
import id.android.codebase.data.repository.MovieRepository
import id.android.codebase.data.repository.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val movieRepository: MovieRepository,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {

    private val _movieData = MutableLiveData<DetailMovieResponse?>()
    val movieData: LiveData<DetailMovieResponse?> get() = _movieData

    private val _videosData = MutableLiveData<DetailMovieVideosResponse?>()
    val videosData: LiveData<DetailMovieVideosResponse?> get() = _videosData

    val voteAverage = MutableLiveData<String>()

    val urlImage = MutableLiveData<String>()

    var page = 0
    private var movieId  = 0

    private val listReviewItem: MutableList<ReviewItem> = mutableListOf()

    private val _reviewItemList = MutableLiveData(mutableListOf<ReviewItem>())
    val reviewItemList: LiveData<MutableList<ReviewItem>> get() = _reviewItemList

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: MutableLiveData<Boolean> get() = _isFavorite

    fun getMovieDetail(id: Int) =
        viewModelScope.launch(appDispatchers.io) {
            movieRepository.getDetailMovie(id).collect {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        val data = it.data
                        _movieData.postValue(data)
                        voteAverage.postValue(data?.voteAverage.toString())
                        urlImage.postValue(Keys.BASE_URL_IMAGE + data?.backdropPath)
                    }

                    else -> Unit
                }
            }
        }

    fun getDataVideos(id: Int) =
        viewModelScope.launch(appDispatchers.io) {
            movieRepository.getDetailMovieVideos(id).collect {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        _videosData.postValue(it.data)
                    }

                    else -> Unit
                }
            }
        }

    fun getReviewMovie(id: Int?) =
        viewModelScope.launch(appDispatchers.io) {
            movieRepository.getReviewMovie(id?: movieId, ++page).collect {
                if (id!= null){
                    movieId = id
                }
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        val data = it.data?.results
                        listReviewItem.addAll(data!!)
                    }
                    else -> Unit
                }
                _reviewItemList.postValue(listReviewItem)
            }

        }

    fun addToFavoriteMovie(data: MovieItem) {
        viewModelScope.launch(appDispatchers.io) {
            movieRepository.addMovieFavorite(data)
        }
    }

    fun deleteFavoriteMovieItem(name: String) {
        viewModelScope.launch(appDispatchers.io) {
            movieRepository.deleteMovieFavoriteItem(name)
        }
    }

    fun checkMovieItemFavorite(id: Int) {
        viewModelScope.launch(appDispatchers.io) {
            movieRepository.checkIfFavoriteItemMovie(id).let {
                _isFavorite.postValue(it)
            }
        }
    }
}