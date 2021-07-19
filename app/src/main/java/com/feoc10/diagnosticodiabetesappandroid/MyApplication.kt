package com.feoc10.diagnosticodiabetesappandroid

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import com.feoc10.diagnosticodiabetesappandroid.di.appModule

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //inject Android context
            androidContext(this@MyApplication)
            // use modules
            modules(appModule)
        }

    }
}