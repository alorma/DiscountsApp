package com.alorma.discounts.ui.discountslist

sealed class ItemView {
    data class DiscountVM(
        val id: String,
        val text: String,
        val date: String?
    ) : ItemView()

    data class PlaceVM(
        val id: String,
        val name: String
    ) : ItemView()

}