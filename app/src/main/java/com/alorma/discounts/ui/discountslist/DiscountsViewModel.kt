package com.alorma.discounts.ui.discountslist

import androidx.lifecycle.liveData
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.ui.base.BaseViewModel

class DiscountsViewModel(
    private val discountsDao: DiscountsDao
) : BaseViewModel() {

    val discounts = liveData {
        emit(discountsDao.getAll())
    }
}