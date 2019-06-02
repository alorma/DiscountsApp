package com.alorma.discounts.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alorma.discounts.data.entity.PlaceEntity

@Dao
interface PlacesDao {

    @Query("SELECT * FROM ${PlaceEntity.TABLE_NAME}")
    suspend fun getAllSync(): List<PlaceEntity>

    @Query("SELECT * FROM ${PlaceEntity.TABLE_NAME}")
    fun getAll(): LiveData<List<PlaceEntity>>

    @Query("SELECT * FROM ${PlaceEntity.TABLE_NAME} WHERE id = :placeId")
    suspend fun getById(placeId: String): PlaceEntity

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(place: PlaceEntity)

    @Insert
    suspend fun insertAll(places: List<PlaceEntity>)

}