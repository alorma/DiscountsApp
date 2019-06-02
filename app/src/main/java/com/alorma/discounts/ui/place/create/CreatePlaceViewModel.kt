package com.alorma.discounts.ui.place.create

import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.datasource.PlacesDataSource
import com.alorma.discounts.domain.NewPlaceModel
import com.alorma.discounts.ui.base.BaseViewModel
import com.alorma.discounts.ui.newdiscount.form.SavePlace
import kotlinx.coroutines.launch
import org.joda.time.LocalTime
import java.util.*

class CreatePlaceViewModel(
    private val placesDatasource: PlacesDataSource
) : BaseViewModel<CreatePlaceViewModel.View>() {

    private var newPlace: NewPlaceModel = NewPlaceModel(
        UUID.randomUUID().toString()
    )

    fun save(name: String, address: String?) {
        if (name.isNotEmpty()) {
            newPlace = newPlace.copy(name = name, address = address)

            viewModelScope.launch {
                placesDatasource.save(newPlace)
                view?.close(SavePlace(newPlace.id, newPlace.name))
            }
        }
    }

    fun onOpenHourSelected(localTime: LocalTime) {
        newPlace = newPlace.copy(openHour = localTime)
    }

    fun onCloseHourSelected(localTime: LocalTime) {
        newPlace = newPlace.copy(closeHour = localTime)
    }

    interface View : BaseView {
        fun close(savePlace: SavePlace)
    }
}