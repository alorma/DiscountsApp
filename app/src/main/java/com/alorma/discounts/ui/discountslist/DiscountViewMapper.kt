package com.alorma.discounts.ui.discountslist

import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.mapper.DateMapper

class DiscountViewMapper(
    private val dateMapper: DateMapper
) {

    fun map(discounts: List<DiscountEntity>): List<DiscountViewModel> = discounts.map {
        mapItem(it)
    }

    fun mapItem(it: DiscountEntity): DiscountViewModel = DiscountViewModel(
        it.code,
        it.text,
        it.place,
        it.expirationDate?.let { dateMapper.mapView(it) },
        mapQuantity(it)
    )

    private fun mapQuantity(it: DiscountEntity): String? =
        it.quantity?.toString()?.let { quantity ->
            it.type?.let { type ->
                when (type) {
                    "percent" -> "$quantity per cent"
                    "currency" -> "$quantity EUR"
                    else -> "$quantity $type"
                }
            }
        }
}