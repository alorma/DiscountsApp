package com.alorma.discounts.ui.discountslist

import com.alorma.discounts.data.entity.DiscountEntity

class DiscountViewMapper {

    fun map(discounts: List<DiscountEntity>): List<DiscountViewModel> {
        return discounts.groupBy { it.used }.flatMap { group ->
            listOf(
                DiscountViewModel.Section(
                    if (group.key) {
                        "Used"
                    } else {
                        "Not used"
                    }
                )
            ) + group.value.map {
                DiscountViewModel.Item(
                    it.code,
                    it.title,
                    it.place,
                    it.expirationDate.toString()
                )
            }
        }
    }
}