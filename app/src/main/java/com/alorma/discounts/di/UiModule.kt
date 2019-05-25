package com.alorma.discounts.di

import com.alorma.discounts.ui.barcode.BarcodeCaptureViewModel
import com.alorma.discounts.ui.discountdetail.DiscountDetailViewModel
import com.alorma.discounts.ui.discountslist.DiscountViewMapper
import com.alorma.discounts.ui.discountslist.DiscountsViewModel
import com.alorma.discounts.ui.mapper.DateMapper
import com.alorma.discounts.ui.newdiscount.NewDiscountViewMapper
import com.alorma.discounts.ui.newdiscount.NewDiscountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { DiscountsViewModel(get(), get()) }
    viewModel { DiscountDetailViewModel(get(), get()) }
    viewModel { BarcodeCaptureViewModel(get()) }
    viewModel { NewDiscountViewModel(get(), get()) }

    factory { DiscountViewMapper(get(), get()) }
    factory { NewDiscountViewMapper() }
    factory { DateMapper() }
}