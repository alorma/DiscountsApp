package com.alorma.discounts.di

import com.alorma.discounts.ui.discountdetail.DiscountDetailFragmentArgs
import com.alorma.discounts.ui.discountdetail.DiscountDetailViewMapper
import com.alorma.discounts.ui.discountdetail.DiscountDetailViewModel
import com.alorma.discounts.ui.discountslist.DiscountViewMapper
import com.alorma.discounts.ui.discountslist.DiscountsViewModel
import com.alorma.discounts.ui.mapper.DateMapper
import com.alorma.discounts.ui.newdiscount.barcode.BarcodeCaptureViewModel
import com.alorma.discounts.ui.newdiscount.form.NewDiscountViewMapper
import com.alorma.discounts.ui.newdiscount.form.NewDiscountViewModel
import com.alorma.discounts.ui.place.select.SelectPlaceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { DiscountsViewModel(get(), get(), get()) }
    viewModel { (args: DiscountDetailFragmentArgs) ->
        DiscountDetailViewModel(args.discountId, get(), get())
    }
    viewModel { BarcodeCaptureViewModel(get()) }
    viewModel { NewDiscountViewModel(get(), get(), get()) }
    viewModel { SelectPlaceViewModel() }

    factory { DiscountViewMapper(get()) }
    factory { DiscountDetailViewMapper(get(), get()) }
    factory { NewDiscountViewMapper() }
    factory { DateMapper() }
}