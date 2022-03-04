package com.example.mvvm.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.ItemTestDataBinding

class TestAdapter(
    val dataItemClick: (TestData) -> Unit,
    val dataItemLongClick: (TestData) -> Unit
) : RecyclerView.Adapter<TestAdapter.ViewHolder>() {
    private var testList = mutableListOf<TestData>()

    inner class ViewHolder(private val binding: ItemTestDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TestData) {
            with(binding) {
                itemTvName.text = data.name
                itemTvNumber.text = data.number
                itemTvInitial.text = data.initial
            }

            itemView.setOnClickListener {
                dataItemClick(data)
            }

            itemView.setOnLongClickListener {
                dataItemLongClick(data)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTestDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(testList[position])
    }

    override fun getItemCount(): Int = testList.size

    fun setTestData(testList: MutableList<TestData>) {
        this.testList = testList
        notifyDataSetChanged()
    }

}