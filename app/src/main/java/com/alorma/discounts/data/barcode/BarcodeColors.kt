package com.alorma.discounts.data.barcode

import com.alorma.discounts.R
import com.alorma.discounts.data.resources.ResourcesProvider

class BarcodeColors(private val resourcesProvider: ResourcesProvider) {

    fun getForeground(): Int = resourcesProvider.getColor(R.color.barcode_foreground)
    fun getBackground(): Int = resourcesProvider.getColor(R.color.barcode_background)
}