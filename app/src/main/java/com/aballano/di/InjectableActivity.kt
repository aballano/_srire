package com.aballano.di

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.aballano.MyApplication

abstract class InjectableActivity : AppCompatActivity(), Consumer {

    @CallSuper override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInject(MyApplication.graph)
    }
}

interface Consumer {
    fun onInject(graph: ApplicationComponent)
}
