package com.alorma.discounts.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alorma.discounts.data.entity.DiscountEntity

@Dao
interface DiscountsDao {

    @Query("SELECT * FROM ${DiscountEntity.TABLE_NAME} ORDER BY expiration DESC")
    suspend fun getAll(): List<DiscountEntity>

    @Query("SELECT * FROM ${DiscountEntity.TABLE_NAME} WHERE place = :place ORDER BY expiration DESC")
    suspend fun getAllByPlace(place: String): List<DiscountEntity>

    @Query("SELECT * FROM ${DiscountEntity.TABLE_NAME} WHERE id = :discountId")
    suspend fun getByIdSync(discountId: String): DiscountEntity

    @Query("SELECT * FROM ${DiscountEntity.TABLE_NAME} WHERE id = :discountId")
    fun getById(discountId: String): LiveData<DiscountEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(discount: DiscountEntity)

    @Insert
    suspend fun insertAll(discounts: List<DiscountEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(discount: DiscountEntity)

    @Query("DELETE FROM ${DiscountEntity.TABLE_NAME} WHERE id = :discountId")
    suspend fun delete(discountId: String)

    @Transaction
    suspend fun updateUsed(discountId: String, used: Boolean) {
        val copy = getByIdSync(discountId).copy(used = used)
        update(copy)
    }
}