package com.alorma.discounts.ui.discountdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.ui.base.BaseViewModel
import com.alorma.discounts.ui.discountslist.DiscountViewMapper
import com.alorma.discounts.ui.discountslist.DiscountViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DiscountDetailViewModel(
    private val discountsDao: DiscountsDao,
    private val mapper: DiscountViewMapper
) : BaseViewModel<DiscountDetailViewModel.View>() {

    val code = MutableLiveData<String>()

    private val _discount = MediatorLiveData<DiscountViewModel>()
    val discount: LiveData<DiscountViewModel>
        get() = _discount

    init {
        _discount.addSource(code) { discountCode ->
            _discount.removeSource(code)
            loadDiscount(discountCode)
        }
    }

    private fun loadDiscount(discountCode: String) {
        viewModelScope.launch {
            val discount = async {
                val entity = discountsDao.getByCode(discountCode)
                mapper.mapItem(entity)
            }

            discount.await().let { discountModel ->
                _discount.postValue(discountModel)
            }
        }
    }

    interface View : BaseView
}