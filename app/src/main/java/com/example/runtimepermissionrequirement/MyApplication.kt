package com.example.runtimepermissionrequirement

import android.app.Application
import com.example.runtimepermissionrequirement.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@MyApplication)
            modules(listOf(viewModel))
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}