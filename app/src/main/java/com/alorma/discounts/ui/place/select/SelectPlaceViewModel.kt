package com.alorma.discounts.ui.place.select

import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.alorma.discounts.data.dao.PlacesDao
import com.alorma.discounts.ui.base.BaseViewModel

class SelectPlaceViewModel(
    placesDao: PlacesDao
) : BaseViewModel<SelectPlaceViewModel.View>() {

    val places = liveData {
        emitSource(placesDao.getAll())
    }.map {
        it.map { entity -> PlaceItemViewModel(entity.id, entity.name) }
    }

    interface View : BaseView
}