package com.alorma.discounts.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PlaceEntity.TABLE_NAME)
data class PlaceEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String
) {
    companion object {
        const val TABLE_NAME = "places"
        const val ID_COLUMN = "id"
    }
}