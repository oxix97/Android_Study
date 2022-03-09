package com.example.myapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemMainBinding
import com.example.myapplication.model.MainData
import com.example.myapplication.viewmodel.MainViewModel

class MainListAdapter(private val viewModel: MainViewModel) :
    ListAdapter<MainData, MainListAdapter.ViewHolder>(MainDataComparator()) {

    inner class ViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MainData) {
            with(binding) {
                executePendingBindings()
                binding.mainData = data

                btnMainDelete.setOnClickListener {
                    viewModel.delete(data)
                }
            }
        }
    }

    private class MainDataComparator : DiffUtil.ItemCallback<MainData>() {
        override fun areItemsTheSame(oldItem: MainData, newItem: MainData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MainData, newItem: MainData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    internal fun setDataList(dataList: List<MainData>) {
        val list: LiveData<List<MainData>> = liveData { dataList }
        viewModel.dataList = list
    }

}