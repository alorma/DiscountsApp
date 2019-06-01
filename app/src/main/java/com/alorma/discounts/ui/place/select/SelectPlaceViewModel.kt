package com.alorma.discounts.ui.place.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alorma.discounts.ui.base.BaseViewModel
import java.util.*

class SelectPlaceViewModel : BaseViewModel<SelectPlaceViewModel.View>() {

    private val _places: MutableLiveData<List<PlaceItemViewModel>> = MutableLiveData()
    val places: LiveData<List<PlaceItemViewModel>>
        get() = _places

    override fun start() {
        super.start()
        val places = listOf(
            PlaceItemViewModel(UUID.randomUUID().toString(), "La sirena"),
            PlaceItemViewModel(UUID.randomUUID().toString(), "Bonpreu & Esclat"),
            PlaceItemViewModel(UUID.randomUUID().toString(), "Caprabo")
        )

        _places.postValue(places)
    }

    interface View : BaseView
}