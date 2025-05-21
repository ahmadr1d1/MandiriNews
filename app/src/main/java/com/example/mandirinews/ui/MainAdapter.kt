package com.example.mandirinews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mandirinews.R
import com.example.mandirinews.data.response.ArticlesItem
import com.example.mandirinews.databinding.ItemRowNewsHorizontalBinding
import com.example.mandirinews.databinding.ItemRowNewsVerticalBinding
import com.example.mandirinews.utils.HelperTime

class MainAdapter : ListAdapter<ArticlesItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        const val VIEW_TYPE_HORIZONTAL = 1
        const val VIEW_TYPE_VERTICAL = 2

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.description == newItem.description
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).viewType == VIEW_TYPE_HORIZONTAL) {
            VIEW_TYPE_HORIZONTAL
        } else {
            VIEW_TYPE_VERTICAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HORIZONTAL -> {
                val binding = ItemRowNewsHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HorizontalViewHolder(binding)
            }

            VIEW_TYPE_VERTICAL -> {
                val binding = ItemRowNewsVerticalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                VerticalViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is HorizontalViewHolder -> holder.bind(item)
            is VerticalViewHolder -> holder.bind(item)
        }
    }

    inner class HorizontalViewHolder(private val binding: ItemRowNewsHorizontalBinding):
            RecyclerView.ViewHolder(binding.root) {
                fun bind(item: ArticlesItem) {
                    Glide.with(binding.root.context)
                        .load(item.urlToImage)
                        .placeholder(R.drawable.loading_image)
                        .into(binding.newsImage)
                    binding.tvTitle.text = item.title
                    binding.tvSrc.text = item.source?.name
                    val date = HelperTime.formatDateOnly(item.publishedAt!!)
                    binding.tvDate.text = date
                }
            }

    inner class VerticalViewHolder(private val binding: ItemRowNewsVerticalBinding):
            RecyclerView.ViewHolder(binding.root) {
                fun bind(item: ArticlesItem) {
                    Glide.with(binding.root.context)
                        .load(item.urlToImage)
                        .placeholder(R.drawable.loading_image)
                        .into(binding.newsImage)
                    binding.tvTitle.text = item.title
                    binding.tvDesc.text = item.description
                }
            }
}