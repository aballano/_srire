@file:Suppress("unused")

package com.aballano.di

import android.app.Application
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Qualifier


@Module
class AndroidModule(private val application: Application) {

    @Provides
    @SchedulerIo
    internal fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @SchedulerMainThread
    internal fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @SchedulerImmediate
    internal fun provideImmediateScheduler(): Scheduler = Schedulers.trampoline()

    @Provides
    @SchedulerComputation
    internal fun provideComputationScheduler(): Scheduler = Schedulers.computation()

}

@MustBeDocumented
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SchedulerImmediate

@MustBeDocumented
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SchedulerComputation

@MustBeDocumented
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SchedulerMainThread

@MustBeDocumented
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SchedulerIo
