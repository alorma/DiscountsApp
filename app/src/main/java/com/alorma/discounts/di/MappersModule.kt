package com.alorma.discounts.di

import com.alorma.discounts.ui.discountslist.DiscountViewMapper
import org.koin.dsl.module

val mappersModule = module {
    factory { DiscountViewMapper() }
}