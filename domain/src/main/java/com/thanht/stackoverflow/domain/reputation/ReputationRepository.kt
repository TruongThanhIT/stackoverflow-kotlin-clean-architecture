package com.thanht.stackoverflow.domain.reputation

import com.thanht.stackoverflow.domain.model.Reputation
import io.reactivex.Observable
import com.thanht.stackoverflow.domain.reputation.request.GetReputationParams

interface ReputationRepository {
    fun getReputationList(requestParams: GetReputationParams?): Observable<List<Reputation>>
}