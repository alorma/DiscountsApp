package com.alorma.discounts.ui.discountslist

import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.mapper.DateMapper

class DiscountViewMapper(
    private val dateMapper: DateMapper) {

    fun map(discounts: List<DiscountEntity>): List<DiscountVM> = discounts.map {
        mapItem(it)
    }

    fun mapItem(it: DiscountEntity): DiscountVM = DiscountVM(
        it.id,
        it.text,
        it.place,
        it.expirationDate?.let { dateMapper.mapView(it) }
    )
}