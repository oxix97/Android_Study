package com.example.remoteconfigtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.remoteconfigtest.databinding.ItemPageBinding

class PageAdapter(
    val revealed: Boolean
) : ListAdapter<RemoteData, PageAdapter.ViewHolder>(ListAdapterUtil<RemoteData>()) {

    inner class ViewHolder(private val binding: ItemPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RemoteData) {
            binding.tvItemName.text = data.name
            binding.tvItemPrice.apply {
                text = data.price.toString()
                visibility = if (revealed) View.VISIBLE
                else View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualPosition = position % currentList.size
        holder.onBind(getItem(actualPosition))
    }

    override fun getItemCount() = Int.MAX_VALUE
}