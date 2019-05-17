package com.alorma.discounts.ui.discountslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alorma.discounts.R
import kotlinx.android.synthetic.main.discount_row.view.*

class DiscountsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var callback: ((DiscountViewModel.Item) -> Unit)? = null

    var items: List<DiscountViewModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_SECTION -> {
                val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
                SectionHolder(view)
            }
            VIEW_ITEM -> DiscountHolder.build(parent) {
                callback?.invoke(it)
            }
            else -> {
                EmptyHolder(View(parent.context))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SectionHolder -> holder.bind(getItem(position) as DiscountViewModel.Section)
            is DiscountHolder -> holder.bind(getItem(position) as DiscountViewModel.Item)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DiscountViewModel.Section -> VIEW_SECTION
            is DiscountViewModel.Item -> VIEW_ITEM
        }
    }

    private fun getItem(position: Int): DiscountViewModel = items[position]

    companion object {
        private const val VIEW_SECTION = 1
        private const val VIEW_ITEM = 2
    }
}

class SectionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(discountViewModel: DiscountViewModel.Section) {
        itemView.findViewById<TextView>(android.R.id.text1).apply {
            text = discountViewModel.title
            setTextColor(ContextCompat.getColor(context, R.color.colorSecondary))
        }
    }
}

class DiscountHolder(
    itemView: View,
    private val callback: ((DiscountViewModel.Item) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {
    fun bind(discountViewModel: DiscountViewModel.Item) {
        itemView.code.text = discountViewModel.code
        itemView.title.text = discountViewModel.title
        itemView.expiration.text = discountViewModel.date
        itemView.place.text = discountViewModel.place

        itemView.setOnClickListener { callback?.invoke(discountViewModel) }
    }

    companion object {

        fun build(
            parent: ViewGroup,
            callback: ((DiscountViewModel.Item) -> Unit)?
        ): DiscountHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.discount_row, parent, false)
            return DiscountHolder(view, callback)
        }
    }
}

class EmptyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)