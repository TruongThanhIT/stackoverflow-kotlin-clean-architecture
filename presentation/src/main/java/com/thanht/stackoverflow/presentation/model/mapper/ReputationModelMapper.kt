package com.thanht.stackoverflow.presentation.model.mapper

import com.thanht.stackoverflow.domain.model.Reputation
import com.thanht.stackoverflow.domain.model.mapper.Mapper
import com.thanht.stackoverflow.presentation.model.ReputationModel

class ReputationModelMapper : Mapper<Reputation, ReputationModel>() {
    override fun revertMap(value: ReputationModel?): Reputation? {
        return if (value != null) {
            Reputation(value.reputationHistoryType, value.reputationChanged, value.postId, value.createdDate)
        } else null
    }

    override fun map(value: Reputation?): ReputationModel? {
        return if (value != null) {
            ReputationModel(value.reputationHistoryType, value.reputationChanged, value.postId, value.createdDate)
        } else null
    }
}
