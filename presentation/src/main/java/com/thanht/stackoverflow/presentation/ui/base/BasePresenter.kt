package com.thanht.stackoverflow.presentation.ui.base


import io.reactivex.disposables.CompositeDisposable
import com.thanht.stackoverflow.domain.base.BaseObserver
import com.thanht.stackoverflow.domain.base.UseCase
import com.thanht.stackoverflow.domain.base.ErrorResponse

abstract class BasePresenter<V : BaseCallback>(callback: V) {
    protected var callback: V? = callback
    private val mCompositeDisposable = CompositeDisposable()

    fun create() {

    }

    fun start() {

    }

    fun pause() {

    }

    fun destroy() {
        mCompositeDisposable.clear()
        mCompositeDisposable.dispose()
    }

    protected fun <T, P> executeTask(useCase: UseCase<T, P>, param: P, observer: BaseObserver<T>) {
        mCompositeDisposable.add(useCase.execute(observer, param))
    }

    protected fun handleError(errorResponse: ErrorResponse) {
        this.callback?.showError(errorResponse.errorMessage)
    }
}
