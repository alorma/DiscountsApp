package com.alorma.discounts.ui.place.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alorma.discounts.R
import com.alorma.discounts.databinding.SelectPlaceRowBinding

class SelectPlaceAdapter : ListAdapter<PlaceItemViewModel, SelectPlaceAdapter.Holder>(DIFF) {

    var callback: (PlaceItemViewModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: SelectPlaceRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.select_place_row,
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), callback)
    }

    class Holder(private val binding: SelectPlaceRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            place: PlaceItemViewModel,
            callback: (PlaceItemViewModel) -> Unit
        ) {
            binding.model = place
            binding.executePendingBindings()
            binding.root.setOnClickListener { callback(place) }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<PlaceItemViewModel>() {
            override fun areItemsTheSame(oldItem: PlaceItemViewModel, newItem: PlaceItemViewModel): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: PlaceItemViewModel, newItem: PlaceItemViewModel): Boolean =
                oldItem.title == newItem.title

        }
    }
}