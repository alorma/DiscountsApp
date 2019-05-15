package com.alorma.discounts.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TestDao {

    @Insert
    suspend fun something()
}