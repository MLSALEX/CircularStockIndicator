package com.alexmls.minichallenges

import android.app.Application
import com.alexmls.minichallenges.circular_indicator.di.circularIndicatorModule
import com.alexmls.minichallenges.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                circularIndicatorModule,
                homeModule
            )
        }
    }
}