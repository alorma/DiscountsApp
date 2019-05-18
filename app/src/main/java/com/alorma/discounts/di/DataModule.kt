package com.alorma.discounts.di

import androidx.room.Room
import com.alorma.discounts.data.DiscountsDatabase
import com.alorma.discounts.data.barcode.BarcodeColors
import com.alorma.discounts.data.barcode.CameraBarcodeFormatMapper
import com.alorma.discounts.data.barcode.DiscountBarcodeGenerator
import com.alorma.discounts.data.barcode.DrawBarcodeFormatMapper
import com.alorma.discounts.data.resources.ResourcesProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            DiscountsDatabase::class.java, DiscountsDatabase.DATABASE_NAME
        ).build()
    }

    single { get<DiscountsDatabase>().discountsDao() }

    single { DiscountBarcodeGenerator(get()) }
    single { BarcodeColors(get()) }
    single { ResourcesProvider(androidApplication()) }

    factory { CameraBarcodeFormatMapper() }
    factory { DrawBarcodeFormatMapper() }
}