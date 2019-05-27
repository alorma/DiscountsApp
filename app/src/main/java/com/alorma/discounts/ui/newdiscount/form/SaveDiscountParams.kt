package com.alorma.discounts.ui.newdiscount.form

import com.alorma.discounts.ui.newdiscount.barcode.BarcodeCaptureResultData
import java.util.*

data class SaveDiscountParams(
        val barcode: BarcodeCaptureResultData? = null,
        val text: String? = null,
        val expirationDate: Date? = null,
        val place: SavePlace? = null
)

data class SavePlace(val id: String, val name: String)