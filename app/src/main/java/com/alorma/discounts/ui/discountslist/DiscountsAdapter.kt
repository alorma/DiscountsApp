package com.alorma.discounts.ui.discountslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alorma.discounts.R
import com.alorma.discounts.extensions.hide
import com.alorma.discounts.extensions.show
import kotlinx.android.synthetic.main.discount_row.view.*

class DiscountsAdapter : RecyclerView.Adapter<DiscountHolder>() {

    var callback: ((DiscountViewModel) -> Unit)? = null

    var items: List<DiscountViewModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountHolder {
        return DiscountHolder.build(parent) {
            callback?.invoke(it)
        }
    }

    override fun onBindViewHolder(holder: DiscountHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = items.size

    private fun getItem(position: Int): DiscountViewModel = items[position]
}

class DiscountHolder(
    itemView: View,
    private val callback: ((DiscountViewModel) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {
    fun bind(discountViewModel: DiscountViewModel) {
        itemView.code.text = discountViewModel.code
        itemView.title.text = discountViewModel.title
        itemView.expiration.text = discountViewModel.date

        if (discountViewModel.date != null) {
            itemView.expiration.show()
            itemView.expiration.text = discountViewModel.date
        } else {
            itemView.expiration.hide()
        }

        if (discountViewModel.place != null) {
            itemView.place.show()
            itemView.placeDivider.show()
            itemView.place.text = discountViewModel.place
        } else {
            itemView.place.hide()
            itemView.placeDivider.hide()
        }

        itemView.setOnClickListener { callback?.invoke(discountViewModel) }
    }

    companion object {

        fun build(
            parent: ViewGroup,
            callback: ((DiscountViewModel) -> Unit)?
        ): DiscountHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.discount_row, parent, false)
            return DiscountHolder(view, callback)
        }
    }
}