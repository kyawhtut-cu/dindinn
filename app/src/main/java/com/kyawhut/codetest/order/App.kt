package com.kyawhut.codetest.order

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}
