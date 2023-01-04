package com.example.reviews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reviews.data.BookInfoData
import com.example.reviews.databinding.ItemBooksBinding
import com.example.reviews.util.ListComparator

class BookListAdapter :
    ListAdapter<BookInfoData, BookListAdapter.ViewHolder>(ListComparator<BookInfoData>()) {
    inner class ViewHolder(
        private val binding: ItemBooksBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BookInfoData) {
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBooksBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}