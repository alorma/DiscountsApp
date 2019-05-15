package com.alorma.discounts.ui.discountslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DiscountsAdapter : ListAdapter<DiscountViewModel, DiscountHolder>(DIFF) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)

        return DiscountHolder(view)
    }

    override fun onBindViewHolder(holder: DiscountHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<DiscountViewModel>() {
            override fun areItemsTheSame(oldItem: DiscountViewModel, newItem: DiscountViewModel): Boolean =
                oldItem.code == newItem.code

            override fun areContentsTheSame(oldItem: DiscountViewModel, newItem: DiscountViewModel): Boolean =
                oldItem.date == newItem.date

        }
    }

}

class DiscountHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(discountViewModel: DiscountViewModel) {
        itemView.findViewById<TextView>(android.R.id.text1).text = discountViewModel.title
        itemView.findViewById<TextView>(android.R.id.text2).text = discountViewModel.code
    }

}