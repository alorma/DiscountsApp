package com.alorma.discounts.domain

sealed class DiscountType {
    object Percentage : DiscountType()
    object Currency : DiscountType()
}