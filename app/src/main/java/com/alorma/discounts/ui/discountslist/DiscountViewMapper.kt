package com.alorma.discounts.ui.discountslist

import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.data.entity.PlaceEntity
import com.alorma.discounts.ui.mapper.DateMapper

class DiscountViewMapper(
    private val dateMapper: DateMapper
) {

    fun map(placesDiscountsList: Map<PlaceEntity, List<DiscountEntity>>): List<ItemView> =
        placesDiscountsList
            .mapValues { map(it.value) }
            .filterValues { it.isNotEmpty() }
            .toList()
            .flatMap { (place, discounts) ->
                listOf(ItemView.PlaceVM(place.id, "${place.name} (${discounts.size})")) + discounts
            }

    fun map(discounts: List<DiscountEntity>): List<ItemView.DiscountVM> = discounts.map {
        mapItem(it)
    }

    private fun mapItem(it: DiscountEntity): ItemView.DiscountVM = ItemView.DiscountVM(
        it.id,
        it.text,
        it.expirationDate?.let { dateMapper.mapView(it) }
    )
}