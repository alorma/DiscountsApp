package com.alorma.discounts.ui.discountslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alorma.discounts.R
import com.alorma.discounts.extensions.hide
import com.alorma.discounts.extensions.show
import kotlinx.android.synthetic.main.discount_row.view.*

class DiscountsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var callback: ((ItemView.DiscountVM) -> Unit)? = null

    var items: List<ItemView> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        VIEW_TYPE_PLACE -> PlaceHolder.build(parent)
        VIEW_TYPE_DISCOUNT -> DiscountHolder.build(parent) {
            callback?.invoke(it)
        }
        else -> throw IllegalStateException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlaceHolder -> holder.bind(getItem(position) as ItemView.PlaceVM)
            is DiscountHolder -> holder.bind(getItem(position) as ItemView.DiscountVM)
        }
    }

    override fun getItemCount(): Int = items.size

    private fun getItem(position: Int): ItemView = items[position]

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ItemView.PlaceVM -> VIEW_TYPE_PLACE
        is ItemView.DiscountVM -> VIEW_TYPE_DISCOUNT
    }

    companion object {
        const val VIEW_TYPE_PLACE = 1
        const val VIEW_TYPE_DISCOUNT = 2
    }
}

class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(placeVM: ItemView.PlaceVM) {
        itemView.findViewById<TextView>(android.R.id.text1).text = placeVM.name
    }

    companion object {
        fun build(
            parent: ViewGroup
        ): PlaceHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return PlaceHolder(view)
        }
    }
}

class DiscountHolder(
    itemView: View,
    private val callback: ((ItemView.DiscountVM) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {
    fun bind(discountVM: ItemView.DiscountVM) {
        itemView.title.text = discountVM.text
        itemView.expiration.text = discountVM.date

        if (discountVM.date != null) {
            itemView.expiration.show()
            itemView.expiration.text = discountVM.date
        } else {
            itemView.expiration.hide()
        }

        itemView.setOnClickListener { callback?.invoke(discountVM) }
    }

    companion object {

        fun build(
            parent: ViewGroup,
            callback: ((ItemView.DiscountVM) -> Unit)?
        ): DiscountHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.discount_row, parent, false)
            return DiscountHolder(view, callback)
        }
    }
}