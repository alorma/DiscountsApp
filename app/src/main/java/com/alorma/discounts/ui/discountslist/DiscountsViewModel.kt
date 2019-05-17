package com.alorma.discounts.ui.discountslist

import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class DiscountsViewModel(
    private val discountsDao: DiscountsDao,
    private val mapper: DiscountViewMapper
) : BaseViewModel<DiscountsViewModel.View>() {

    fun onInit() {
        viewModelScope.launch {
            loadItems()
        }
    }

    private suspend fun loadItems() {
        val discountItems = discountsDao.getAll()
        val models = mapper.map(discountItems)
        view?.showDiscounts(models)
    }

    fun onCreateNew() {
        view?.openNewDiscount()
    }

    interface View : BaseView {
        fun showDiscounts(discounts: List<DiscountViewModel>)

        fun openNewDiscount()
    }
}