package com.thanht.stackoverflow.domain.reputation.interactors

import com.thanht.stackoverflow.domain.base.UseCase
import com.thanht.stackoverflow.domain.model.Reputation
import com.thanht.stackoverflow.domain.reputation.ReputationRepository
import com.thanht.stackoverflow.domain.reputation.request.GetReputationParams
import com.thanht.stackoverflow.domain.scheduler.ResultScheduler
import com.thanht.stackoverflow.domain.scheduler.WorkScheduler
import io.reactivex.Observable
import javax.inject.Inject

class GetReputationUseCase @Inject internal constructor(workScheduler: WorkScheduler,
                                                        resultScheduler: ResultScheduler,
                                                        private val repository: ReputationRepository)
    : UseCase<List<Reputation>, GetReputationParams>(workScheduler, resultScheduler) {

    override fun buildUseCaseObservable(params: GetReputationParams?): Observable<List<Reputation>> {
        return repository.getReputationList(params)
    }
}