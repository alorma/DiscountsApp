package com.alorma.discounts.domain.usecase

import com.alorma.discounts.data.datasource.DiscountsDataSource
import com.alorma.discounts.domain.Result
import com.alorma.discounts.ui.newdiscount.SaveDiscountParams

class SaveDiscountUseCase(
    private val discountsDataSource: DiscountsDataSource
) {

    suspend fun save(saveDiscount: SaveDiscountParams): Result<*> = discountsDataSource.save(saveDiscount)
}