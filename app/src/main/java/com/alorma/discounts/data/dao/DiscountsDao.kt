package com.alorma.discounts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alorma.discounts.data.entity.DiscountEntity

@Dao
interface DiscountsDao {

    @Query("SELECT * FROM ${DiscountEntity.TABLE_NAME}")
    suspend fun getAll(): List<DiscountEntity>

    @Insert
    suspend fun insert(discount: DiscountEntity)

    @Insert
    suspend fun insertAll(discounts: List<DiscountEntity>)
}