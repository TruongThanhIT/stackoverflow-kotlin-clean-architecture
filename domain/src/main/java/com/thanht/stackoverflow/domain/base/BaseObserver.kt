package com.thanht.stackoverflow.domain.base

import io.reactivex.observers.DisposableObserver

abstract class BaseObserver<T> {
    val subscriber: DisposableObserver<T>

    init {
        subscriber = object : DisposableObserver<T>() {
            override fun onStart() {
                super.onStart()
                onSubscriber()
            }

            override fun onComplete() {
                onBeforeEnd(true)
            }

            override fun onError(e: Throwable?) {
                onBeforeEnd(false)
            }

            override fun onNext(t: T) {
                onHandleSuccess(t)
            }
        }
    }

    protected open fun onSubscriber() {

    }

    protected open fun onHandleSuccess(t: T) {

    }

    protected open fun onBeforeEnd(isSuccess: Boolean) {

    }

    protected open fun onHandleError(errorResponse: ErrorResponse) {

    }
}
