package com.alorma.discounts.ui.discountdetail

import com.alorma.discounts.data.barcode.DrawBarcodeFormatMapper
import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.mapper.DateMapper
import net.codecision.glidebarcode.model.Barcode

class DiscountDetailViewMapper(
    private val dateMapper: DateMapper,
    private val barcodeMapper: DrawBarcodeFormatMapper
) {

    fun mapItem(it: DiscountEntity): DiscountDetailVM = DiscountDetailVM(
        it.id,
        createBarcode(it),
        it.text,
        it.place,
        it.expirationDate?.let { dateMapper.mapView(it) }
    )

    private fun createBarcode(it: DiscountEntity): Barcode? =
        if (it.code != null && it.format != null) {
            Barcode(it.code, barcodeMapper.map(it.format))
        } else {
            null
        }

}