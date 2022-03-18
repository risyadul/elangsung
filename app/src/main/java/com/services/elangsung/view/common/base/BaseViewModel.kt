package com.services.elangsung.view.common.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    open fun reloadPage() = run { }

    protected fun Disposable.collect() = compositeDisposable.add(this)

    fun collect(disposable: Disposable) {
        disposable.collect()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
