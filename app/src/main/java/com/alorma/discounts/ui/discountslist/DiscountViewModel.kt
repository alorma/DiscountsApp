package com.alorma.discounts.ui.discountslist

sealed class DiscountViewModel {

    data class Section(
        val title: String
    ) : DiscountViewModel()

    data class Item(
        val code: String,
        val title: String,
        val place: String?,
        val date: String?
    ) : DiscountViewModel()
}