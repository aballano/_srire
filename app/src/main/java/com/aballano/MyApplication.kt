package com.aballano

import android.app.Application
import com.aballano.di.AndroidModule
import com.aballano.di.ApplicationComponent
import com.aballano.di.DaggerApplicationComponent

class MyApplication : Application() {

    companion object {
        // platformStatic allow access it from java code
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
        graph.inject(this)
    }
}