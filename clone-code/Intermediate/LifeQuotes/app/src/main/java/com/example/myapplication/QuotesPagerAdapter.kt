package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemQuoteBinding

class QuotesPagerAdapter(
    private val quotes: List<Quote>
) : RecyclerView.Adapter<QuotesPagerAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Quote) {
            with(binding) {
                tvItemTitle.text = data.name
                tvItemQuote.text = data.quote
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(quotes[position])
    }

    override fun getItemCount() = quotes.size
}