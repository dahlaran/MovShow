package com.dahlaran.movshow.application

import android.app.Application
import android.content.Context
import com.dahlaran.movshow.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.lang.ref.WeakReference

@HiltAndroidApp
class MovShowApplication : Application() {
    companion object{
        var context: WeakReference<Context> = WeakReference(null)
    }

    override fun onCreate() {
        super.onCreate()
        context = WeakReference(this)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}