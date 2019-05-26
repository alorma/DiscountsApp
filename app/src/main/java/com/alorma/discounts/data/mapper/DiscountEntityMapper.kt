package com.alorma.discounts.data.mapper

import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.barcode.BarcodeCaptureResultData
import com.alorma.discounts.ui.newdiscount.SavePlace
import java.util.*

class DiscountEntityMapper {

    fun mapSave(
        barcode: BarcodeCaptureResultData?,
        text: String?,
        expirationDate: Date?,
        place: SavePlace?
    ): DiscountEntity = DiscountEntity(
        UUID.randomUUID().toString(),
        barcode?.code,
        barcode?.format?.name,
        text.orEmpty(),
        place?.id,
        expirationDate?.time
    )
}