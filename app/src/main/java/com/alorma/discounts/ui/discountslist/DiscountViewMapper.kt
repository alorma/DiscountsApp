package com.alorma.discounts.ui.discountslist

import com.alorma.discounts.data.entity.DiscountEntity

class DiscountViewMapper {

    fun map(discounts: List<DiscountEntity>): List<DiscountViewModel> = discounts.map {
        DiscountViewModel(
            it.code,
            it.title,
            it.place,
            it.expirationDate.toString()
        )
    }
}