package id.android.codebase.features.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.android.codebase.data.model.ReviewItem
import id.android.codebase.features.databinding.ItemReviewBinding

class ReviewAdapter(
) : ListAdapter<ReviewItem, ReviewAdapter.ViewHolder>(object : DiffUtil.ItemCallback<ReviewItem>() {
    override fun areItemsTheSame(oldItem: ReviewItem, newItem: ReviewItem) = oldItem.id == oldItem.id
    override fun areContentsTheSame(oldItem: ReviewItem, newItem: ReviewItem) = oldItem.id == oldItem.id
}) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder){
            with(getItem(position)) {
                binding.tvAuthorName.text = author
                binding.tvReviewMovie.text = content
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)    }

    inner class ViewHolder(val binding: ItemReviewBinding)
        : RecyclerView.ViewHolder(binding.root)
}