@file:Suppress("unused")

package com.aballano.common

import com.aballano.di.SchedulerComputation
import com.aballano.di.SchedulerImmediate
import com.aballano.di.SchedulerIo
import com.aballano.di.SchedulerMainThread
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import javax.inject.Inject

/**
 * Provides observable transformers that simplify the usage of [Observable.subscribeOn]
 * and [Observable.observeOn] methods.
 */
open class TransformersProvider @Inject constructor(@SchedulerIo internal val io: Scheduler,
                                                    @SchedulerImmediate internal val immediate: Scheduler,
                                                    @SchedulerComputation internal val computation: Scheduler,
                                                    @SchedulerMainThread internal val mainThread: Scheduler) {

    fun <T> ioTransformer(): ObservableTransformer<T, T> = transformer(io)
    fun <T> immediateTransformer(): ObservableTransformer<T, T> = transformer(immediate)
    fun <T> computationTransformer(): ObservableTransformer<T, T> = transformer(computation)

    fun <T> ioSingleTransformer(): SingleTransformer<T, T> = singleTransformer(io)
    fun <T> computationSingleTransformer(): SingleTransformer<T, T> = singleTransformer(io)
    fun <T> immediateSingleTransformer(): SingleTransformer<T, T> = singleTransformer(io)
}

fun <T> TransformersProvider.transformer(scheduler: Scheduler) =
      ObservableTransformer <T, T> { it.subscribeOn(scheduler).observeOn(mainThread) }

fun <T> TransformersProvider.singleTransformer(scheduler: Scheduler) =
      SingleTransformer<T, T> { it.subscribeOn(scheduler).observeOn(mainThread) }