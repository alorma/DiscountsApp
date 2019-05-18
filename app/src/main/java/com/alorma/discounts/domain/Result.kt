package com.alorma.discounts.domain

sealed class Result<T> {
    sealed class Success<T> : Result<T>() {
        object Complete : Success<Any>()
        data class Data<T>(val t: T) : Result<T>()
    }

    data class Error(val t: Throwable) : Result<Throwable>()
}