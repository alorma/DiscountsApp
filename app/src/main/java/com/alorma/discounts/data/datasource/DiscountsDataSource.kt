package com.alorma.discounts.data.datasource

import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.data.mapper.DiscountEntityMapper
import com.alorma.discounts.domain.SaveDiscount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DiscountsDataSource(
    private val discountsDao: DiscountsDao,
    private val discountMapper: DiscountEntityMapper
) {

    suspend fun save(saveDiscount: SaveDiscount) = withContext(Dispatchers.IO) {
        val entity = discountMapper.mapSave(saveDiscount)
        discountsDao.insert(entity)
    }
}