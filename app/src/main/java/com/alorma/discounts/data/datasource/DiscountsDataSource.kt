package com.alorma.discounts.data.datasource

import android.database.sqlite.SQLiteConstraintException
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.data.mapper.DiscountEntityMapper
import com.alorma.discounts.domain.Result
import com.alorma.discounts.domain.SaveDiscount
import com.alorma.discounts.domain.error.DiscountExistException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DiscountsDataSource(
    private val discountsDao: DiscountsDao,
    private val discountMapper: DiscountEntityMapper
) {

    suspend fun save(saveDiscount: SaveDiscount): Result<*> = withContext(Dispatchers.IO) {
        try {
            val entity = discountMapper.mapSave(saveDiscount)
            discountsDao.insert(entity)
            Result.Success.Complete
        } catch (e: Exception) {
            (e as? SQLiteConstraintException)?.let {
                Result.Error(DiscountExistException())
            } ?: Result.Error(e)
        }
    }
}