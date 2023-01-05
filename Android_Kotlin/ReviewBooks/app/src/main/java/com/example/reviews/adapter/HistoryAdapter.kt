package com.example.reviews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reviews.data.History
import com.example.reviews.databinding.ItemHistorysBinding
import com.example.reviews.util.ListComparator

class HistoryAdapter(
    val clickListener: (String) -> Unit
) : ListAdapter<History, HistoryAdapter.ViewHolder>(ListComparator()) {
    inner class ViewHolder(
        private val binding: ItemHistorysBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: History) {
            binding.tvItemTitle.text = data.keyword
            binding.ibtnItemDelete.setOnClickListener {
                clickListener(data.keyword.orEmpty())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistorysBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}