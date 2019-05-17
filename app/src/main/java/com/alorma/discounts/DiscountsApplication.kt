package com.alorma.discounts

import android.app.Application
import com.alorma.discounts.di.dataModule
import com.alorma.discounts.di.mappersModule
import com.alorma.discounts.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DiscountsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DiscountsApplication)
            modules(dataModule, mappersModule, uiModule)
        }
    }
}