package com.alorma.discounts.di

import com.alorma.discounts.ui.discountslist.DiscountsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { DiscountsViewModel(get()) }
}