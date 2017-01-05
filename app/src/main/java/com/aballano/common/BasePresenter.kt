package com.aballano.common

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/** Abstraction for the Presenters in a MVP pattern.  */
abstract class BasePresenter<V : BaseView> {

    protected val compositeSubscription = CompositeDisposable()
    lateinit var view: V

    @CallSuper open fun bind(view: V) {
        this.view = view
    }

    @CallSuper fun unbind() {
        compositeSubscription.dispose()
    }

    protected fun clearSubscriptions() {
        compositeSubscription.clear()
    }

    protected fun addSubscription(disposable: Disposable) {
        compositeSubscription.add(disposable)
    }
}
