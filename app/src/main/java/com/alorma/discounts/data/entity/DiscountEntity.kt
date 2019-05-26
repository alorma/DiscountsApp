package com.alorma.discounts.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alorma.discounts.data.entity.DiscountEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DiscountEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "code") val code: String?,
    @ColumnInfo(name = "format") val format: String?,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "place") val place: String?,
    @ColumnInfo(name = "expiration") val expirationDate: Long?,
    @ColumnInfo(name = "used") val used: Boolean = false
) {

    companion object {
        const val TABLE_NAME = "discounts"
    }
}