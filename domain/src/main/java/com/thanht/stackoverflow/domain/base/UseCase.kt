package com.thanht.stackoverflow.domain.base

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import com.thanht.stackoverflow.domain.scheduler.ResultScheduler
import com.thanht.stackoverflow.domain.scheduler.WorkScheduler
import io.reactivex.disposables.Disposable

abstract class UseCase<T, Params> internal constructor(private val workScheduler: WorkScheduler,
                                                       private val resultScheduler: ResultScheduler) {
    private var disposables: Disposable? = null

    internal abstract fun buildUseCaseObservable(params: Params?): Observable<T>

    fun execute(observer: BaseObserver<T>, params: Params?): Disposable {
        disposables = this.buildUseCaseObservable(params)
                .subscribeOn(workScheduler.scheduler)
                .observeOn(resultScheduler.scheduler).subscribeWith(observer.subscriber)
        return disposables!!
    }

    fun dispose() {
        if (disposables?.isDisposed!!) {
            disposables?.dispose()
        }
    }
}
