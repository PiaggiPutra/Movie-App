package id.android.codebase.features.home

import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import id.android.codebase.common.base.BaseFragment
import id.android.codebase.features.R
import id.android.codebase.features.databinding.FragmentHomeBinding
import id.android.codebase.features.home.adapter.CategoryAdapter
import id.android.codebase.features.home.adapter.MovieAdapter
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by inject()
    override fun getLayoutResId() = R.layout.fragment_home
    private var isFirstShow : Boolean = true

    private val movieAdapter by lazy {
        MovieAdapter {
            viewModel.navigateToDetail(it.id)
        }
    }

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter{
            viewModel.getMovieList(it.id)
            if (!isFirstShow) {
                viewModel.page = 0
                viewModel.listMovieItem.clear()
                movieAdapter.submitList(null)
                movieAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun main() {
        initView()
        viewModel.getGenre()
        setupObserver()
        setupListener()
    }
    private fun initView(){
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        binding?.apply {
            viewModelBinding = viewModel
            rvMovie.adapter = movieAdapter
            rvCategory.adapter = categoryAdapter
            ibFavoriteHome.setOnClickListener {
                viewModel.navigateToFavorite()
            }
        }
    }

    override fun setupListener() {
        super.setupListener()
        binding?.apply {
            nsvHome.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
                if (v.getChildAt(v.childCount - 1) != null) {
                    if (scrollY >= v.getChildAt(v.childCount - 1)
                            .measuredHeight - v.measuredHeight &&
                        scrollY > oldScrollY
                    ) {
                        viewModel.getMovieList(null)
                    }
                }
            }
        }
    }

    override fun setupObserver() {
        viewModel.apply {
            movieItemList.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()){
                    isFirstShow = false
                    movieAdapter.submitList(it)
                    movieAdapter.notifyDataSetChanged()
                }
            }
        }
        viewModel.genreMovieList.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty() && isFirstShow){
                viewModel.getMovieList(it[0].id)
            }
            categoryAdapter.submitList(it.toList())
            categoryAdapter.notifyDataSetChanged()

        }
    }
}