package com.alorma.discounts.ui.discountslist

import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.launch


class DiscountsViewModel(
    private val discountsDao: DiscountsDao,
    private val mapper: DiscountViewMapper
) : BaseViewModel<DiscountsViewModel.View>() {

    override fun start() {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            val discountItems = discountsDao.getAll()
            val models = mapper.map(discountItems)
            view?.showDiscounts(models)
        }
    }

    fun onCreateNew() {
        view?.openNewDiscount()
    }

    fun onDiscountClick(discount: DiscountViewModel.Item) {
        view?.openDetail(discount.code)
    }

    interface View : BaseView {
        fun showDiscounts(discounts: List<DiscountViewModel>)


        fun openNewDiscount()
        fun openDetail(code: String)
    }
}