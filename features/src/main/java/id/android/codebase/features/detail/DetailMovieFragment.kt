package id.android.codebase.features.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import id.android.codebase.common.base.BaseFragment
import id.android.codebase.common.utils.keys.Keys
import id.android.codebase.data.model.MovieItem
import id.android.codebase.features.R
import id.android.codebase.features.databinding.FragmentDetailMovieBinding
import id.android.codebase.features.detail.adapter.ReviewAdapter
import id.android.codebase.features.detail.adapter.VideosAdapter
import org.koin.android.ext.android.inject

class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding, DetailMovieViewModel>() {

    override val viewModel: DetailMovieViewModel by inject()
    override fun getLayoutResId() = R.layout.fragment_detail_movie
    private val args: DetailMovieFragmentArgs by navArgs()
    private var isFirstShow : Boolean = true
    private var dataFavorite : MovieItem? = null


    private val videosAdapter by lazy {
        VideosAdapter {
            openUrl(Keys.BASE_YT_URL + it.key)
        }
    }
    private val reviewAdapter by lazy {
        ReviewAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.id != null){
            viewModel.getMovieDetail(args.id)
            viewModel.getDataVideos(args.id)
            viewModel.getReviewMovie(args.id)
            viewModel.checkMovieItemFavorite(args.id)
            observer()
        }
    }

    override fun main() {
        binding?.viewModelBinding = viewModel
        initView()
    }

    private fun initView(){
        binding?.apply {
            rvVideos.adapter = videosAdapter
            rvReview.adapter = reviewAdapter
        }
    }

    private fun initImage(){
        binding?.apply {
            Glide.with(requireContext()).asDrawable()
                .load(viewModel.urlImage.value)
                .into(ivPosterDetail)
        }
    }

    private fun observer(){
        viewModel.movieData.observe(viewLifecycleOwner){
            if (it != null){
                dataFavorite = MovieItem(it.id, it.originalTitle, it.posterPath, it.voteAverage)
                viewModel.checkMovieItemFavorite(it.id)
                handleFavoriteItem(dataFavorite!!)
            }
        }
        viewModel.isFavorite.observe(viewLifecycleOwner){
            if (it != null && !isFirstShow){
                handleFavoriteItem(dataFavorite!!)
            }
        }
        viewModel.urlImage.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()) initImage()
        }
        viewModel.videosData.observe(viewLifecycleOwner){
            videosAdapter.submitList(it?.results)
            videosAdapter.notifyDataSetChanged()
        }

        viewModel.apply {
            reviewItemList.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()){
                    isFirstShow = false
                    reviewAdapter.submitList(it)
                    reviewAdapter.notifyDataSetChanged()
                }
            }
        }
    }
    private fun handleFavoriteItem(data: MovieItem) {
        binding?.apply {
            if (viewModel?.isFavorite?.value == true) {
                ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_black)
                ivFavoriteDetail.setOnClickListener {
                    viewModel?.deleteFavoriteMovieItem(data.originalTitle)
                    Toast.makeText(requireContext(), getString(R.string.text_remove_favorite_item), Toast.LENGTH_SHORT).show()
                    ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_border_black)
                    viewModel?.checkMovieItemFavorite(data.id)
                }
            }else{
                ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_border_black)
                ivFavoriteDetail.setOnClickListener {
                    viewModel?.addToFavoriteMovie(data)
                    ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_black)
                    Toast.makeText(requireContext(), getString(R.string.text_add_favorite_item), Toast.LENGTH_SHORT).show()
                    viewModel?.checkMovieItemFavorite(data.id)
                }
            }
        }
    }
    override fun setupListener() {
        super.setupListener()
        binding?.apply {
            nsvDetailMovie.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
                if (v.getChildAt(v.childCount - 1) != null) {
                    if (scrollY >= v.getChildAt(v.childCount - 1)
                            .measuredHeight - v.measuredHeight &&
                        scrollY > oldScrollY
                    ) {
                        viewModel.getReviewMovie(null)
                    }
                }
            }
        }
    }
}