package com.services.elangsung.domain.common.base

import com.services.elangsung.domain.CompletableErrorTransformer
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseCompletableUseCase(private val errorTransformer: CompletableErrorTransformer) {

    abstract fun buildSingle(data: Map<String, Any?> = emptyMap()): Completable

    fun execute(data: Map<String, Any?> = emptyMap()): Completable {
        return buildSingle(data)
            .compose(errorTransformer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
