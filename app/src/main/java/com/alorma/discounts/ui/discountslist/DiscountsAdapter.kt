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

    var callback: ((DiscountVM) -> Unit)? = null

    var items: List<DiscountVM> = listOf()
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

    private fun getItem(position: Int): DiscountVM = items[position]
}

class DiscountHolder(
    itemView: View,
    private val callback: ((DiscountVM) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {
    fun bind(discountVM: DiscountVM) {
        itemView.title.text = discountVM.text
        itemView.expiration.text = discountVM.date

        if (discountVM.date != null) {
            itemView.expiration.show()
            itemView.expiration.text = discountVM.date
        } else {
            itemView.expiration.hide()
        }

        if (discountVM.place != null) {
            itemView.place.show()
            itemView.placeDivider.show()
            itemView.place.text = discountVM.place
        } else {
            itemView.place.hide()
            itemView.placeDivider.hide()
        }

        itemView.setOnClickListener { callback?.invoke(discountVM) }
    }

    companion object {

        fun build(
            parent: ViewGroup,
            callback: ((DiscountVM) -> Unit)?
        ): DiscountHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.discount_row, parent, false)
            return DiscountHolder(view, callback)
        }
    }
}