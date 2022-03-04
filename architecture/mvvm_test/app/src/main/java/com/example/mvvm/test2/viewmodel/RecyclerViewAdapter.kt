package com.example.mvvm.test2.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.ItemRecyclerBinding
import com.example.mvvm.test2.model.Entity

class RecyclerViewAdapter internal constructor(
    var onDeleteListener: MainViewModel
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var users = emptyList<Entity>() // Cached copy of words

    inner class ViewHolder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Entity) {
            binding.text.text = data.num
            with(binding) {
                deleteButton.setOnClickListener {
                    onDeleteListener.delete(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(users[position])
    }

    internal fun setUsers(users: List<Entity>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size
}