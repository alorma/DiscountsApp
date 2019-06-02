package com.alorma.discounts.ui.discountslist

import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.data.entity.PlaceEntity
import com.alorma.discounts.ui.formatter.DateFormat
import com.alorma.discounts.ui.formatter.TimeFormat

class DiscountViewMapper(
    private val dateFormat: DateFormat,
    private val timeFormat: TimeFormat
) {

    fun map(placesDiscountsList: Map<PlaceEntity, List<DiscountEntity>>): List<ItemView> =
        placesDiscountsList
            .mapValues { mapDiscount(it.value) }
            .filterValues { it.isNotEmpty() }
            .toList()
            .flatMap { (place, discounts) ->
                listOf(mapPlace(place, discounts)) + discounts
            }

    private fun mapPlace(place: PlaceEntity, discounts: List<ItemView.DiscountVM>): ItemView.PlaceVM =
        ItemView.PlaceVM(
            place.id, "${place.name} (${discounts.size})",
            place.address,
            place.openHour?.let { timeFormat.format(it) },
            place.closeHour?.let { timeFormat.format(it) }
        )

    private fun mapDiscount(discounts: List<DiscountEntity>): List<ItemView.DiscountVM> =
        discounts.map {
            mapItem(it)
        }

    private fun mapItem(it: DiscountEntity): ItemView.DiscountVM = ItemView.DiscountVM(
        it.id,
        it.text,
        it.expirationDate?.let { dateFormat.mapView(it) }
    )
}