package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerItemBinding

class RecyclerViewAdapter internal constructor(
    context: Context,
    var onDeleteListener: MainViewModel
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var users = emptyList<Entity>() // Cached copy of words

    inner class ViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Entity) {
            with(binding) {
                text.text = data.number1
                deleteButton.setOnClickListener {
                    onDeleteListener.deleteAll(users[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
