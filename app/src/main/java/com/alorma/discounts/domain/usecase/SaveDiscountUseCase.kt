package com.alorma.discounts.domain.usecase

import com.alorma.discounts.data.datasource.DiscountsDataSource
import com.alorma.discounts.domain.Result
import com.alorma.discounts.domain.SaveDiscount

class SaveDiscountUseCase(
    private val discountsDataSource: DiscountsDataSource
) {

    suspend fun save(saveDiscount: SaveDiscount): Result<*> = discountsDataSource.save(saveDiscount)
}