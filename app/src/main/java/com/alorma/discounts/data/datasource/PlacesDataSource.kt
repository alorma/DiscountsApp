package com.alorma.discounts.data.datasource

import com.alorma.discounts.data.dao.PlacesDao
import com.alorma.discounts.data.entity.PlaceEntity
import com.alorma.discounts.domain.NewPlaceModel

class PlacesDataSource(private val placesDao: PlacesDao) {

    suspend fun save(newPlaceModel: NewPlaceModel) {
        val entity = PlaceEntity(
            newPlaceModel.id,
            newPlaceModel.name,
            newPlaceModel.address,
            newPlaceModel.openHour?.millisOfDay,
            newPlaceModel.closeHour?.millisOfDay
        )

        placesDao.insert(entity)
    }
}