package com.alorma.discounts.ui.discountslist

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.data.dao.PlacesDao
import com.alorma.discounts.data.entity.PlaceEntity
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class DiscountsViewModel(
    private val placesDao: PlacesDao,
    private val discountsDao: DiscountsDao,
    private val mapper: DiscountViewMapper
) : BaseViewModel<DiscountsViewModel.View>() {

    override fun start() {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {

            if (placesDao.getAll().isEmpty()) {
                val places = (1..3).map {
                    PlaceEntity("place_$it", "Place $it")
                } + PlaceEntity("la-sirena", "La Sirena")

                placesDao.insertAll(places)

                placesDao.getAll().forEach {
                    Log.i("Alorma", it.toString())
                }
            }

            val map = placesDao.getAll()
                .associateWith {
                    discountsDao.getAllByPlace(it.id)
                }

            val items = mapper.map(map)
            view?.showList(items)
        }
    }

    fun onCreateNew() {
        view?.openNewDiscount()
    }

    fun onDiscountClick(discount: ItemView.DiscountVM) {
        view?.openDetail(discount.id)
    }

    interface View : BaseView {
        fun showList(items: List<ItemView>)

        fun openNewDiscount()
        fun openDetail(discountId: String)
    }
}