package com.example.chapter5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter5.data.RecyclerViewData
import com.example.chapter5.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat


class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>() {
    var listData = mutableListOf<RecyclerViewData>()

    class Holder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "클릭된 아이템: ${binding.tvNo.text}번째 아이템",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

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