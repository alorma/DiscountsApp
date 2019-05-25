package com.alorma.discounts.ui.discountslist

import com.alorma.discounts.data.barcode.DrawBarcodeFormatMapper
import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.mapper.DateMapper
import net.codecision.glidebarcode.model.Barcode

class DiscountViewMapper(
    private val dateMapper: DateMapper,
    private val barcodeFormatMapper: DrawBarcodeFormatMapper
) {

    fun map(discounts: List<DiscountEntity>): List<DiscountViewModel> = discounts.map {
        mapItem(it)
    }

    fun mapItem(it: DiscountEntity): DiscountViewModel = DiscountViewModel(
        it.code,
        createBarcode(it),
        it.text,
        it.place,
        it.expirationDate?.let { dateMapper.mapView(it) },
        mapQuantity(it)
    )

    private fun createBarcode(it: DiscountEntity): Barcode =
        Barcode(it.code, barcodeFormatMapper.map(it.format))

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