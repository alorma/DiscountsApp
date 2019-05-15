package com.alorma.discounts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.data.entity.DiscountEntity

@Database(entities = [DiscountEntity::class], version = 1)
abstract class DiscountsDatabase : RoomDatabase() {
    abstract fun discountsDao(): DiscountsDao

    companion object {
        const val DATABASE_NAME = "discounts_database"
    }
}