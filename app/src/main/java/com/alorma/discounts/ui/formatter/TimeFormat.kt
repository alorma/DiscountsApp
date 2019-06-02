package com.alorma.discounts.ui.formatter

import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat

class TimeFormat {

    fun format(time: LocalTime): String {
        return DateTimeFormat.forPattern("HH:mm").print(time)
    }

    fun format(time: Int): String {
        return DateTimeFormat.forPattern("HH:mm").print(LocalTime.fromMillisOfDay(time.toLong()))
    }
}