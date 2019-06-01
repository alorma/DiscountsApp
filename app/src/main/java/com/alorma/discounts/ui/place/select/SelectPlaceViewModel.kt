package com.alorma.discounts.ui.place.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.dao.PlacesDao
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SelectPlaceViewModel(
    private val placesDao: PlacesDao
) : BaseViewModel<SelectPlaceViewModel.View>() {

    private val _places: MutableLiveData<List<PlaceItemViewModel>> = MutableLiveData()
    val places: LiveData<List<PlaceItemViewModel>>
        get() = _places

    override fun start() {
        super.start()

        viewModelScope.launch {
            val places = placesDao.getAll().map {
                PlaceItemViewModel(it.id, it.name)
            }

            _places.postValue(places)
        }
    }

    interface View : BaseView
}