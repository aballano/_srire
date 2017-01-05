package com.aballano.di

import com.aballano.MyApplication
import com.aballano.srire.startrek.presentation.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {

    fun inject(application: MyApplication)
    fun inject(activity: MainActivity)
}