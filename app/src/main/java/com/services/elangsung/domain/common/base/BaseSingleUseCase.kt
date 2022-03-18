package com.services.elangsung.domain.common.base

import com.services.elangsung.domain.common.SingleErrorTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseSingleUseCase<T>(private val errorTransformer: SingleErrorTransformer<T>) {

    abstract fun buildSingle(data: Map<String, Any?> = emptyMap()): Single<T>

    fun execute(data: Map<String, Any?> = emptyMap()): Single<T> {
        return buildSingle(data)
            .compose(errorTransformer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
