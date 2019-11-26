package com.thanht.stackoverflow.presentation.ui.userlist

import com.thanht.stackoverflow.presentation.model.UserInfoModel
import com.thanht.stackoverflow.presentation.ui.base.BaseCallback

interface UserListCallback : BaseCallback {
    fun getUserInfoListSuccess(dataList: List<UserInfoModel>, isLoadMore: Boolean)
    fun onUserBookMarked(userInfoModel: UserInfoModel)
    fun onUserBookMarkRemoved(userInfoModel: UserInfoModel)
}