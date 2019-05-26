package com.alorma.discounts.domain

sealed class Result<T> {
    sealed class Success<T> : Result<T>() {
        object Complete : Success<Any>()
        data class Data<T>(val t: T) : Result<T>()
    }

    sealed class Fail(open val t: Throwable) : Result<Throwable>() {
        data class Unsuccessful(override val t: Throwable) : Fail(t)
        data class Error(override val t: Throwable) : Fail(t)
    }
}