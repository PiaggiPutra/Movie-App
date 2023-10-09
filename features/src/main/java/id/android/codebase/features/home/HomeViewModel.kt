package id.android.codebase.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.android.codebase.common.base.BaseViewModel
import id.android.codebase.data.model.MovieItem
import id.android.codebase.data.model.response.GenresResponse
import id.android.codebase.data.repository.AppDispatchers
import id.android.codebase.data.repository.MovieRepository
import id.android.codebase.data.repository.utils.Resource
import id.android.codebase.navigation.NavGraphBaseDirections
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val appDispatchers: AppDispatchers
) : BaseViewModel(){
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    var page = 0
    private var genresId  = 0

    val listMovieItem: MutableList<MovieItem> = mutableListOf()

    private val _movieItemList = MutableLiveData(mutableListOf<MovieItem>())
    val movieItemList: LiveData<MutableList<MovieItem>> get() = _movieItemList

    private val _genreMovieList = MutableLiveData(mutableListOf<GenresResponse.Genre>())
    val genreMovieList: LiveData<MutableList<GenresResponse.Genre>> get() = _genreMovieList

    fun getGenre() =
        viewModelScope.launch(appDispatchers.io) {
            movieRepository.getListGenre().collect{
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        val data = it.data
                        _genreMovieList.postValue(data?.genres?.toMutableList())
                    }
                    else -> Unit
                }
            }
        }

    fun getMovieList(genres : Int?) =
        viewModelScope.launch(appDispatchers.io) {
            _isLoading.postValue(true)
            movieRepository.getListMovie(genres ?: genresId, ++page).collect {
                if (genres!= null){
                    genresId = genres
                }
                when (it.status) {
                    Resource.Status.ERROR -> {
                        _isLoading.postValue(false)
                    }
                    Resource.Status.SUCCESS -> {
                        _isLoading.postValue(false)
                        val data = it.data?.results
                        listMovieItem.addAll(data!!)
                    }
                    else -> Unit
                }
                _movieItemList.postValue(listMovieItem)
            }
        }
    fun navigateToDetail(id : Int) {
        navigate(NavGraphBaseDirections.actionGlobalDetailFlow(id))
    }
    fun navigateToFavorite() {
        navigate(NavGraphBaseDirections.actionGlobalFavoriteFlow())
    }
}

