package com.alorma.discounts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alorma.discounts.data.entity.DiscountEntity

@Dao
interface DiscountsDao {

    @Query("SELECT * FROM ${DiscountEntity.TABLE_NAME} ORDER BY expiration DESC")
    suspend fun getAll(): List<DiscountEntity>

    @Query("SELECT * FROM ${DiscountEntity.TABLE_NAME} WHERE place = :place ORDER BY expiration DESC")
    suspend fun getAllByPlace(place: String): List<DiscountEntity>

    @Query("SELECT * FROM ${DiscountEntity.TABLE_NAME} WHERE id = :discountId")
    suspend fun getById(discountId: String): DiscountEntity

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(discount: DiscountEntity)

    @Insert
    suspend fun insertAll(discounts: List<DiscountEntity>)

    @Query("DELETE FROM ${DiscountEntity.TABLE_NAME} WHERE id = :discountId")
    suspend fun delete(discountId: String)
}