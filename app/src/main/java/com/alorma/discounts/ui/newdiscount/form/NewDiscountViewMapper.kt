package com.alorma.discounts.ui.newdiscount.form

import com.alorma.discounts.domain.error.DiscountExistException

class NewDiscountViewMapper {

    fun mapError(t: Throwable): String = when (t) {
        is DiscountExistException -> "Discount already existing"
        else -> throw t
    }
}