package it.nicolapiriottu.demo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import it.nicolapiriottu.demo.util.SharedPref
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    companion object {
        @get:Synchronized
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        // Keep ref for current instance
        instance = this
        // Setup Timber
        setupTimber()

        SharedPref.initSharedPrefs()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}