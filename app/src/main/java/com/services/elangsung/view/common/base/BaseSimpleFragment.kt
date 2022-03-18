package com.services.elangsung.view.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding

abstract class BaseSimpleFragment<T : BaseViewModel, V : ViewBinding> : BaseFragment<V>() {

    protected val viewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveDataObservers()
    }

    @CallSuper
    protected open fun initLiveDataObservers() {
    }
}
