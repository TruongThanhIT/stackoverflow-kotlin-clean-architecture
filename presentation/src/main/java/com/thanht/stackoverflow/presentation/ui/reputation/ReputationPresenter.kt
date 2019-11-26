package com.thanht.stackoverflow.presentation.ui.reputation

import com.thanht.stackoverflow.domain.SITE
import javax.inject.Inject

import com.thanht.stackoverflow.domain.base.BaseObserver
import com.thanht.stackoverflow.domain.reputation.interactors.GetReputationUseCase
import com.thanht.stackoverflow.domain.model.Reputation
import com.thanht.stackoverflow.domain.reputation.request.GetReputationParams
import com.thanht.stackoverflow.presentation.model.mapper.ReputationModelMapper
import com.thanht.stackoverflow.presentation.ui.base.BasePresenter

const val MAX_ITEM_IN_PAGE = 30

class ReputationHistoryPresenter(callback: ReputationCallback) : BasePresenter<ReputationCallback>(callback) {
    @Inject
    lateinit var getReputationHistories: GetReputationUseCase

    private lateinit var requestParams: GetReputationParams
    private var page = 1
    private var mNumberItemLoaded: Int = 0

    fun getReputationHistoryList(userId: Int) {
        callback?.showLoading()
        page = 1
        requestParams = GetReputationParams(page, MAX_ITEM_IN_PAGE, SITE, userId)
        executeTask(getReputationHistories, requestParams, object : BaseObserver<List<Reputation>>() {

            override fun onHandleSuccess(t: List<Reputation>) {
                super.onHandleSuccess(t)
                mNumberItemLoaded = t.size
                callback?.loadReputationHistorySuccess(ReputationModelMapper().map(t), false)
            }

            override fun onBeforeEnd(isSuccess: Boolean) {
                super.onBeforeEnd(isSuccess)
                callback?.hideLoading()
            }
        })
    }

    fun loadMore(userId: Int) {
        ++page
        requestParams = GetReputationParams(page, MAX_ITEM_IN_PAGE, SITE, userId)
        executeTask(getReputationHistories, requestParams, object : BaseObserver<List<Reputation>>() {
            override fun onHandleSuccess(t: List<Reputation>) {
                mNumberItemLoaded = t.size
                callback?.loadReputationHistorySuccess(ReputationModelMapper().map(t), true)
            }
        })
    }

    val isLoadAll: Boolean
        get() = mNumberItemLoaded < MAX_ITEM_IN_PAGE
}
