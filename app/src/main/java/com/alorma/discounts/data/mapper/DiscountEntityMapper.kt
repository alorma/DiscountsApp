package com.alorma.discounts.data.mapper

import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.domain.DiscountType
import com.alorma.discounts.domain.SaveDiscount

class DiscountEntityMapper {

    fun mapSave(saveDiscount: SaveDiscount): DiscountEntity = DiscountEntity(
        saveDiscount.code,
        saveDiscount.format.name,
        saveDiscount.text,
        "BonPreu",
        false,
        saveDiscount.expirationDate?.time,
        saveDiscount.discountQuantity,
        mapDiscountType(saveDiscount.discountType)
    )

    private fun mapDiscountType(saveDiscount: DiscountType?): String? =
        when (saveDiscount) {
            DiscountType.Percentage -> "percent"
            DiscountType.Currency -> "currency"
            null -> null
        }
}