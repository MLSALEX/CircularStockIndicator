package com.alexmls.circularstocktracker

import android.app.Application
import com.alexmls.circularstocktracker.di.productModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(productModule)
        }
    }
}