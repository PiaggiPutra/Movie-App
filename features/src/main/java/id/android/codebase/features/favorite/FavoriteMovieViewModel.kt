package id.android.codebase.features.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.android.codebase.common.base.BaseViewModel
import id.android.codebase.data.model.MovieItem
import id.android.codebase.data.repository.AppDispatchers
import id.android.codebase.data.repository.MovieRepository
import id.android.codebase.navigation.NavGraphBaseDirections
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(
    private val movieRepository: MovieRepository,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {

    private val _movieItemList = MutableLiveData(mutableListOf<MovieItem>())
    val movieItemList: LiveData<MutableList<MovieItem>> get() = _movieItemList

    fun getMovie() {
        viewModelScope.launch(appDispatchers.io) {
            _movieItemList.postValue(movieRepository.getAllFavoriteMovie().toMutableList())
        }
    }

    fun navigateToDetail(data : MovieItem){
        navigate(NavGraphBaseDirections.actionGlobalDetailFlow(data.id))
    }
}