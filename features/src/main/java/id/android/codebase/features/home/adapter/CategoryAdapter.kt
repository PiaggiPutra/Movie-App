package id.android.codebase.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.android.codebase.data.model.response.GenresResponse
import id.android.codebase.features.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val onClickListener: (GenresResponse.Genre) -> Unit
) : ListAdapter<GenresResponse.Genre, CategoryAdapter.ViewHolder>(object : DiffUtil.ItemCallback<GenresResponse.Genre>() {
    override fun areItemsTheSame(oldItem: GenresResponse.Genre, newItem: GenresResponse.Genre) = oldItem.id == oldItem.id
    override fun areContentsTheSame(oldItem: GenresResponse.Genre, newItem: GenresResponse.Genre) = oldItem.id == oldItem.id
}) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder){
            with(getItem(position)) {
                binding.tvTitleCategory.text = name

                holder.itemView.setOnClickListener {
                    onClickListener(getItem(position))
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)    }

    inner class ViewHolder(val binding: ItemCategoryBinding)
        : RecyclerView.ViewHolder(binding.root)
}