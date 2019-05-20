package com.alorma.discounts.ui.mapper

import java.text.SimpleDateFormat
import java.util.*

class DateMapper {

    fun mapView(date: Long): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
    }
}