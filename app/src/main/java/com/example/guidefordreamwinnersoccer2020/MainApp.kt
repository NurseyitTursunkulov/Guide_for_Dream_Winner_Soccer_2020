package com.example.guidefordreamwinnersoccer2020

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@MainApp)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(offlineWeatherApp)
        }
    }
    private val offlineWeatherApp: Module = module {
        viewModel{
            MainViewModel()
        }
    }
}