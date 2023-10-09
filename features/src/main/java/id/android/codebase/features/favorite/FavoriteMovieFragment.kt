package id.android.codebase.features.favorite

import androidx.appcompat.app.AppCompatActivity
import id.android.codebase.common.base.BaseFragment
import id.android.codebase.features.R
import id.android.codebase.features.databinding.FragmentFavoriteMovieBinding
import id.android.codebase.features.home.adapter.MovieAdapter
import org.koin.android.ext.android.inject

class FavoriteMovieFragment : BaseFragment<FragmentFavoriteMovieBinding, FavoriteMovieViewModel>() {

    override val viewModel: FavoriteMovieViewModel by inject()

    override fun getLayoutResId() = R.layout.fragment_favorite_movie

    private val favoriteAdapter by lazy { MovieAdapter {
        viewModel.navigateToDetail(it)
    } }

    override fun main() {
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        binding?.apply {
            rvMovieFavorite.adapter = favoriteAdapter
        }
        viewModel.getMovie()
        setupObserver()
    }

    override fun setupObserver(){
        viewModel.apply {
            movieItemList.observe(viewLifecycleOwner){
                favoriteAdapter.submitList(it)
                favoriteAdapter.notifyDataSetChanged()
            }
        }
    }
}