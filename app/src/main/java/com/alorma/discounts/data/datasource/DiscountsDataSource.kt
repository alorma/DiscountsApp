package com.alorma.discounts.data.datasource

import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.data.mapper.DiscountEntityMapper
import com.alorma.discounts.domain.Result
import com.alorma.discounts.ui.newdiscount.SaveDiscountParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DiscountsDataSource(
    private val discountsDao: DiscountsDao,
    private val discountMapper: DiscountEntityMapper
) {

    suspend fun save(saveDiscount: SaveDiscountParams): Result<*> = withContext(Dispatchers.IO) {
        try {
            val entity = discountMapper.mapSave(
                saveDiscount.barcode,
                saveDiscount.text,
                saveDiscount.expirationDate,
                saveDiscount.place
            )
            discountsDao.insert(entity)
            Result.Success.Complete
        } catch (e: Exception) {
            Result.Fail.Error(e)
        }
    }
}