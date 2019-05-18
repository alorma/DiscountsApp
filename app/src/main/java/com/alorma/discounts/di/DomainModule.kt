package com.alorma.discounts.di

import com.alorma.discounts.domain.usecase.SaveDiscountUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { SaveDiscountUseCase(get()) }
}