package com.alorma.discounts.ui.discountslist

data class DiscountViewModel(
    val code: String,
    val title: String,
    val place: String?,
    val date: String?
)