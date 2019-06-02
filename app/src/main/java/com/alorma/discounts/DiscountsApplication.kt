package com.alorma.discounts

import android.app.Application
import com.alorma.discounts.di.dataModule
import com.alorma.discounts.di.domainModule
import com.alorma.discounts.di.uiModule
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DiscountsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        JodaTimeAndroid.init(this)

        startKoin {
            androidContext(this@DiscountsApplication)
            modules(listOf(dataModule, domainModule, uiModule))
        }
    }
}
