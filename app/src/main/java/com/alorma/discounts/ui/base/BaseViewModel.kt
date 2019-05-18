package com.alorma.discounts.ui.base

import androidx.lifecycle.*

open class BaseViewModel<T : BaseViewModel.BaseView> :
    ViewModel(), LifecycleObserver {

    var view: T? = null
        set(value) {
            (value as? LifecycleOwner)?.lifecycle?.addObserver(this)
            field = value
        }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun start() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun resume() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun pause() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun stop() {}

    override fun onCleared() {
        super.onCleared()
        (view as? LifecycleOwner)?.lifecycle?.removeObserver(this)
    }

    interface BaseView
}