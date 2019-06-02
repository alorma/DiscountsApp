package com.alorma.discounts.domain

import org.joda.time.LocalTime

data class NewPlaceModel(
    val id: String,
    val name: String = "",
    val address: String? = null,
    val openHour: LocalTime? = null,
    val closeHour: LocalTime? = null
)