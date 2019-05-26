package com.alorma.discounts.ui.discountdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DiscountDetailViewModel(
    discountId: String,
    private val discountsDao: DiscountsDao,
    private val mapper: DiscountDetailViewMapper
) : BaseViewModel<DiscountDetailViewModel.View>() {

    private val _discount = MediatorLiveData<DiscountDetailVM>()
    val discount: LiveData<DiscountDetailVM>
        get() = _discount

    init {
        loadDiscount(discountId)
    }

    private fun loadDiscount(discountId: String) {
        viewModelScope.launch {
            val discount = async {
                val entity = discountsDao.getById(discountId)
                mapper.mapItem(entity)
            }

            discount.await().let { discountModel ->
                _discount.postValue(discountModel)
            }
        }
    }

    interface View : BaseView
}