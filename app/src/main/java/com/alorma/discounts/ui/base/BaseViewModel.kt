package com.alorma.discounts.ui.base

import androidx.lifecycle.ViewModel

open class BaseViewModel<T : BaseViewModel.BaseView> : ViewModel() {

    var view: T? = null

    interface BaseView
}