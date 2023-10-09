package id.android.codebase.features.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.android.codebase.common.utils.keys.Keys
import id.android.codebase.data.model.response.DetailMovieVideosResponse
import id.android.codebase.features.databinding.ItemVideosBinding

class VideosAdapter(
    private val onClickListener: (DetailMovieVideosResponse.Result) -> Unit
) : ListAdapter<DetailMovieVideosResponse.Result, VideosAdapter.ViewHolder>(object : DiffUtil.ItemCallback<DetailMovieVideosResponse.Result>() {
    override fun areItemsTheSame(oldItem: DetailMovieVideosResponse.Result, newItem: DetailMovieVideosResponse.Result) = oldItem.id == oldItem.id
    override fun areContentsTheSame(oldItem: DetailMovieVideosResponse.Result, newItem: DetailMovieVideosResponse.Result) = oldItem.id == oldItem.id
}) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder){
            with(getItem(position)) {

                Glide.with(holder.itemView.context).asDrawable()
                    .load(Keys.BASE_URL_YT_THUMBNAIL + key + "/hqdefault.jpg")
                    .into( binding.ivThumbnail)

                holder.binding.ibPlay.setOnClickListener {
                    onClickListener(getItem(position))
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideosBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)    }

    inner class ViewHolder(val binding: ItemVideosBinding)
        : RecyclerView.ViewHolder(binding.root)
}