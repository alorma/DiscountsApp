package com.alorma.discounts.domain

import java.util.*

data class SaveDiscount(
    val code: String,
    val format: BarcodeFormat,
    val discountQuantity: Double?,
    val discountType: DiscountType?,
    val expirationDate: Date?,
    val text: String
)