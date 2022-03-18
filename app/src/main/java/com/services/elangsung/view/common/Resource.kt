package com.services.elangsung.view.common

class Resource<T>(val status: Status) {

    var item: T? = null

    var exception: Throwable? = null

    constructor(status: Status, item: T?) : this(status) {
        this.item = item
    }

    constructor(status: Status, exception: Throwable) : this(status) {
        this.exception = exception
    }
}
