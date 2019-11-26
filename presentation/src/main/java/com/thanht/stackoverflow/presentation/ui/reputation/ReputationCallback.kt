package com.thanht.stackoverflow.presentation.ui.reputation

import com.thanht.stackoverflow.presentation.model.ReputationModel
import com.thanht.stackoverflow.presentation.ui.base.BaseCallback

interface ReputationCallback : BaseCallback {
    fun loadReputationHistorySuccess(modelList: List<ReputationModel>, isLoadMore: Boolean)
}
