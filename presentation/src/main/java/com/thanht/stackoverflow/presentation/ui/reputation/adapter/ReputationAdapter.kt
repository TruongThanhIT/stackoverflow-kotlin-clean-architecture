package com.thanht.stackoverflow.presentation.ui.reputation.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thanht.stackoverflow.R
import com.thanht.stackoverflow.presentation.model.ReputationModel

class ReputationAdapter : RecyclerView.Adapter<ReputationHolder>() {
    private val reputationModelList = mutableListOf<ReputationModel>()

    fun loadData(data: List<ReputationModel>) {
        reputationModelList.clear()
        if (data.isNotEmpty()) {
            reputationModelList.addAll(data)
        }
        notifyDataSetChanged()
    }

    fun addMoreData(dataList: List<ReputationModel>) {
        if (dataList.isNotEmpty()) {
            reputationModelList.addAll(dataList)
            notifyItemRangeInserted(reputationModelList.size - dataList.size, dataList.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReputationHolder {
        return ReputationHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_reputation_history, parent, false))
    }

    override fun onBindViewHolder(holder: ReputationHolder, position: Int) {
        holder.bindData(reputationModelList[position])
    }

    override fun getItemCount(): Int = reputationModelList.size
}
