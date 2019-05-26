package com.alorma.discounts.ui.discountdetail

import net.codecision.glidebarcode.model.Barcode

data class DiscountDetailVM(
    val id: String,
    val barcode: Barcode?,
    val title: String,
    val place: String?,
    val date: String?
)