package com.alorma.discounts.ui.discountslist

import net.codecision.glidebarcode.model.Barcode

data class DiscountViewModel(
    val code: String,
    val barcode: Barcode,
    val title: String,
    val place: String?,
    val date: String?,
    val discount: String?
)