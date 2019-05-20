package com.alorma.discounts.ui.discountslist

import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.mapper.DateMapper

class DiscountViewMapper(
    private val dateMapper: DateMapper
) {

    fun map(discounts: List<DiscountEntity>): List<DiscountViewModel> {

        val usedItems = discounts.filter { it.used }.mapItems()
        val notUsedItems = discounts.filterNot { it.used }.mapItems()

        val usedItemsSection = if (usedItems.isNotEmpty()) {
            listOf(DiscountViewModel.Section("Used")) + usedItems
        } else {
            listOf()
        }
        val notUsedItemsSection = if (notUsedItems.isNotEmpty()) {
            listOf(DiscountViewModel.Section("Not used")) + notUsedItems
        } else {
            listOf()
        }

        return notUsedItemsSection + usedItemsSection
    }

    private fun List<DiscountEntity>.mapItems() = map {
        mapItem(it)
    }

    fun mapItem(it: DiscountEntity): DiscountViewModel.Item = DiscountViewModel.Item(
        it.code,
        it.text,
        it.place,
        it.expirationDate?.let { dateMapper.mapView(it) }
    )
}