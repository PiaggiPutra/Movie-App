package id.android.codebase.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.android.codebase.common.utils.keys.Keys
import id.android.codebase.data.model.MovieItem
import id.android.codebase.features.databinding.ItemMovieBinding

class MovieAdapter(
    private val onClickListener: (MovieItem) -> Unit
) : ListAdapter<MovieItem, MovieAdapter.ViewHolder>(object : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem) = oldItem.id == oldItem.id
    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem) = oldItem.id == oldItem.id
}) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder){
            with(getItem(position)) {
                binding.tvMovieName.text = originalTitle
                binding.tvRateMovie.text = voteAverage.toString()
                Glide.with(holder.itemView.context).asDrawable()
                    .load(Keys.BASE_URL_IMAGE + posterPath)
                    .into(binding.ivPoster)

                holder.itemView.setOnClickListener {
                    onClickListener(getItem(position))
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)    }

    inner class ViewHolder(val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root)
}