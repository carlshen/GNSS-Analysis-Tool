package com.abecerra.gnssanalysis.app

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.abecerra.gnssanalysis.BuildConfig
import com.abecerra.gnssanalysis.app.di.AppModule
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.core.context.startKoin
import timber.log.Timber

class App : MultiDexApplication() {

    companion object {

        lateinit var INSTANCE: App

        fun getAppContext(): App = INSTANCE

        fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initKoin()
        initTimber()
        FirebaseCrashlytics.getInstance().setUserId("carl")
    }

    private fun initKoin() {
        startKoin {
            modules(AppModule.get())
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
