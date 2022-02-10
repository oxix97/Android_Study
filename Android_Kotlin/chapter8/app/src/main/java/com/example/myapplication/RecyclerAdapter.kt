package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    val dataList = mutableListOf<RoomMemo>()
    var helper: RoomHelper? = null

    inner class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var mRoomMemo: RoomMemo? = null

        init {
            binding.btnRecyclerDelete.setOnClickListener {
                helper?.roomMemoDao()?.delete(mRoomMemo!!)
                dataList.remove(mRoomMemo)
                notifyItemRemoved(adapterPosition)
            }
        }

        fun onBind(data: RoomMemo) {
            with(binding) {
                tvRecyclerNumber.text = "${data.num}"
                tvRecyclerAbout.text = data.content
                val sdf = SimpleDateFormat("yyyy/mm/dd hh:mm")
                tvRecyclerDate.text = "${sdf.format(data.datetime)}"
                mRoomMemo = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val roomMemo = dataList.get(position)
        holder.onBind(roomMemo)
    }

    override fun getItemCount(): Int = dataList.size
}