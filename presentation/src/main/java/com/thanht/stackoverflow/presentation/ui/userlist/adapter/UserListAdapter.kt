package com.thanht.stackoverflow.presentation.ui.userlist.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thanht.stackoverflow.R
import com.thanht.stackoverflow.presentation.model.UserInfoModel

class UserListAdapter(private val mListener: UserListAdapterListener) : RecyclerView.Adapter<UserListHolder>() {
    private val mUserInfoModelList = mutableListOf<UserInfoModel>()

    fun loadData(dataList: List<UserInfoModel>) {
        mUserInfoModelList.clear()
        if (dataList.isNotEmpty()) {
            mUserInfoModelList.addAll(dataList)
        }
        notifyDataSetChanged()
    }

    fun addMoreData(dataList: List<UserInfoModel>) {
        if (dataList.isNotEmpty()) {
            mUserInfoModelList.addAll(dataList)
            notifyItemRangeInserted(mUserInfoModelList.size - dataList.size, dataList.size)
        }
    }

    fun getItem(position: Int): UserInfoModel? = mUserInfoModelList.getOrNull(position)

    fun updateItemBookMarked(userId: Int, isBookMarked: Boolean) = mUserInfoModelList.indexOfFirst { it.userId == userId }.let {
        if (it != RecyclerView.NO_POSITION) {
            mUserInfoModelList[it].isBookMarked = isBookMarked
            notifyItemChanged(it)
        }
    }

    fun removeItem(userInfoModel: UserInfoModel) {
        val pos = mUserInfoModelList.indexOfFirst { it.userId == userInfoModel.userId }
        if (pos != RecyclerView.NO_POSITION) {
            mUserInfoModelList.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListHolder {
        return UserListHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false), mListener)
    }

    override fun onBindViewHolder(holder: UserListHolder, position: Int) {
        holder.bindData(mUserInfoModelList[position])
    }

    override fun getItemCount(): Int = mUserInfoModelList.size
}