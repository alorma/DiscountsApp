package com.alorma.discounts.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alorma.discounts.data.entity.DiscountEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DiscountEntity(
    @PrimaryKey val code: String,
    @ColumnInfo(name = "format") val format: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "place") val place: String?,
    @ColumnInfo(name = "used") val used: Boolean,
    @ColumnInfo(name = "expiration") val expirationDate: Long?,
    @ColumnInfo(name = "quantity") val quantity: Double?,
    @ColumnInfo(name = "type") val type: String?
) {

    companion object {
        const val TABLE_NAME = "discounts"
    }
}