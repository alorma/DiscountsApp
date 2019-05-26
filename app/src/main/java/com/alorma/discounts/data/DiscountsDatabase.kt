package com.alorma.discounts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alorma.discounts.data.DiscountsDatabase.Companion.DATABASE_VERSION
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.data.dao.PlacesDao
import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.data.entity.PlaceEntity

@Database(
    entities = [DiscountEntity::class, PlaceEntity::class],
    version = DATABASE_VERSION,
    exportSchema = true
)
abstract class DiscountsDatabase : RoomDatabase() {
    abstract fun discountsDao(): DiscountsDao
    abstract fun placesDao(): PlacesDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "discounts_database"
    }
}