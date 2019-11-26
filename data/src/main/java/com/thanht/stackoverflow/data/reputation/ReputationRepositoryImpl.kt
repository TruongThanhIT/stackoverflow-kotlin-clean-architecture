package com.thanht.stackoverflow.data.reputation

import com.thanht.stackoverflow.data.util.toReputationList
import com.thanht.stackoverflow.domain.model.Reputation
import com.thanht.stackoverflow.domain.reputation.ReputationRepository
import com.thanht.stackoverflow.domain.reputation.request.GetReputationParams
import io.reactivex.Observable

class ReputationRepositoryImpl(private val service: ReputationService) : ReputationRepository {

    override fun getReputationList(requestParams: GetReputationParams?): Observable<List<Reputation>> =
            if (requestParams != null) {
                service.getReputationList(requestParams.userId,
                        requestParams.page, requestParams.pageSize, requestParams.site)
                        .flatMap { return@flatMap Observable.just(it.userList.toReputationList()) }
            } else {
                Observable.just(emptyList())
            }
}