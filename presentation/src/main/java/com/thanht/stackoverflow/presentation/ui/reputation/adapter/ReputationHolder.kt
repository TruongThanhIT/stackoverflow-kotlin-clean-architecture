package com.thanht.stackoverflow.presentation.ui.reputation.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.thanht.stackoverflow.R

import com.thanht.stackoverflow.presentation.model.ReputationModel
import com.thanht.stackoverflow.presentation.ui.util.getDateTime

class ReputationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mTvReputationChanged = itemView.findViewById<TextView>(R.id.tv_reputation_changed)
    private val mTvCreatedDate = itemView.findViewById<TextView>(R.id.tv_creation_date)
    private val mTvType = itemView.findViewById<TextView>(R.id.tv_reputation_type)
    private val mTvPostId = itemView.findViewById<TextView>(R.id.tv_post_id)

    fun bindData(model: ReputationModel?) {
        model?.let {
            mTvReputationChanged.text = String.format("Change: %d", it.reputationChanged)
            mTvCreatedDate.text = String.format("Created At: %s", getDateTime(it.createdDate.times(1000)))
            mTvType.text = String.format("Reputation Type: %s", it.reputationHistoryType)
            mTvPostId.text = String.format("Post ID: %d", it.postId)
        }
    }
}
