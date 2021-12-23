package com.example.chapter5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter5.data.RecyclerViewData
import com.example.chapter5.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat


class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>() {
    var listData = mutableListOf<RecyclerViewData>()

    class Holder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setMemo(data: RecyclerViewData) {
            binding.tvNo.text = "${data.no}"
            binding.tvTitle.text = "${data.title}"
            val sdf = SimpleDateFormat("yyyy/MM/dd")
            val formattedDate = sdf.format(data.timestamp)
            binding.tvTimeStamp.text = formattedDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = listData.get(position)
        holder.setMemo(data)
    }

    override fun getItemCount(): Int = listData.size

}