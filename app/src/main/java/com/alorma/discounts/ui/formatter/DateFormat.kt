package com.alorma.discounts.ui.formatter

import org.joda.time.format.DateTimeFormat

class DateFormat {

    fun mapView(date: Long): String {
        return DateTimeFormat.forPattern("dd/MM/yyyy").print(date)
    }
}