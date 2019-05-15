package com.alorma.discounts.ui.discountslist

import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class DiscountsViewModel(
    private val discountsDao: DiscountsDao
) : BaseViewModel<DiscountsViewModel.View>() {

    fun onInit() {
        viewModelScope.launch {
            loadItems()
        }
    }

    private suspend fun loadItems() {
        val discountItems = discountsDao.getAll()
        view?.showDiscounts(discountItems)
    }

    fun onCreateNew() {
        viewModelScope.launch {
            val entity = DiscountEntity(
                UUID.randomUUID().toString(),
                "Discount X",
                "La sirena",
                System.currentTimeMillis() + TimeUnit.DAYS.toMillis(4)
            )
            discountsDao.insert(entity)
            loadItems()
        }
    }

    interface View : BaseView {
        fun showDiscounts(discounts: List<DiscountEntity>)
    }
}