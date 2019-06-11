package com.alorma.discounts.ui.discountdetail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.data.getDistinct
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class DiscountDetailViewModel(
    discountId: String,
    private val discountsDao: DiscountsDao,
    private val mapper: DiscountDetailViewMapper
) : BaseViewModel<DiscountDetailViewModel.View>() {

    val discount: LiveData<DiscountDetailVM> = discountsDao.getById(discountId)
        .getDistinct()
        .map {
            mapper.mapItem(it)
        }

    fun onDeleteClick() {
        discount.value?.title?.let { discountName ->
            view?.showDeleteConfirmation(discountName)
        }
    }

    fun onDeleteConfirm() {
        viewModelScope.launch {
            discount.value?.id?.let { discountId ->
                (view as? LifecycleOwner)?.let {
                    discount.removeObservers(it)
                }
                discountsDao.delete(discountId)
                view?.close()
            }
        }
    }

    fun changeUsedStatus() {
        viewModelScope.launch {
            discount.value?.let { discount ->
                discountsDao.updateUsed(discount.id, discount.used.not())
            }
        }
    }

    interface View : BaseView {
        fun showDeleteConfirmation(name: String)
        fun close()
    }
}