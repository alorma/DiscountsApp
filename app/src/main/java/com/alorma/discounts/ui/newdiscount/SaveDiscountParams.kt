package com.alorma.discounts.ui.newdiscount

import com.alorma.discounts.domain.BarcodeFormat
import com.alorma.discounts.domain.DiscountType
import java.util.*

data class SaveDiscountParams(
    val code: String? = null,
    val format: BarcodeFormat? = null,
    val discountType: DiscountType? = null,
    val expirationDate: Date? = null,
    val quantity: Double? = null
) {
    fun isValid() = code != null && format != null && if (quantity != null) {
        discountType != null
    } else {
        true
    }
}