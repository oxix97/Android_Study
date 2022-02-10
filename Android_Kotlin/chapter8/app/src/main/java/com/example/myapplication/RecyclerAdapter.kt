package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    val dataList = mutableListOf<Memo>()
    var helper: SqliteHelper? = null

    inner class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var mMemo: Memo? = null

        init {
            binding.btnRecyclerDelete.setOnClickListener {
                helper?.deleteMemo(mMemo!!)
                dataList.remove(mMemo)
                notifyItemRemoved(adapterPosition)
            }
        }

        fun onBind(data: Memo) {
            with(binding) {
                tvRecyclerNumber.text = "${data.num}"
                tvRecyclerAbout.text = data.content
                val sdf = SimpleDateFormat("yyyy/mm/dd hh:mm")
                tvRecyclerDate.text = "${sdf.format(data.datetime)}"
                mMemo = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = dataList.get(position)
        holder.onBind(memo)
    }

    override fun getItemCount(): Int = dataList.size
}