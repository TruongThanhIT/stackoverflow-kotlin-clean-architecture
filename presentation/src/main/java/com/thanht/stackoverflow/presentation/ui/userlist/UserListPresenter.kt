package com.thanht.stackoverflow.presentation.ui.userlist

import com.thanht.stackoverflow.domain.SITE
import com.thanht.stackoverflow.domain.base.BaseObserver
import com.thanht.stackoverflow.domain.userlist.interactors.GetUserListUseCase
import com.thanht.stackoverflow.domain.userlist.interactors.GetUsersBookMarkedUseCase
import com.thanht.stackoverflow.domain.userlist.interactors.RemoveUserBookMarkedUseCase
import com.thanht.stackoverflow.domain.userlist.interactors.SaveUserBookMarkedUseCase
import com.thanht.stackoverflow.domain.userlist.requests.GetUserListParams
import com.thanht.stackoverflow.domain.model.UserInfo
import com.thanht.stackoverflow.presentation.model.UserInfoModel
import com.thanht.stackoverflow.presentation.model.mapper.UserInfoModelMapper
import com.thanht.stackoverflow.presentation.ui.base.BasePresenter
import dagger.Lazy
import javax.inject.Inject

const val MAX_ITEM_IN_PAGE = 30

class UserListPresenter(callback: UserListCallback) : BasePresenter<UserListCallback>(callback) {

    @Inject
    lateinit var mGetUserList: GetUserListUseCase

    @Inject
    lateinit var mGetUserBookMarked: GetUsersBookMarkedUseCase

    @Inject
    lateinit var mSaveUserBookMarked: Lazy<SaveUserBookMarkedUseCase>

    @Inject
    lateinit var mRemoveUserBookMarked: Lazy<RemoveUserBookMarkedUseCase>

    private lateinit var requestParams: GetUserListParams
    private var page = 1
    private var mNumberItemLoaded: Int = 0
    private val bookMarkedHashMap: HashMap<Int, UserInfoModel> = hashMapOf()

    fun getUserList() {
        callback?.showLoading()
        page = 1
        requestParams = GetUserListParams(page, MAX_ITEM_IN_PAGE, SITE)
        executeTask(mGetUserList, requestParams, object : BaseObserver<List<UserInfo>>() {
            override fun onBeforeEnd(isSuccess: Boolean) {
                callback?.hideLoading()
            }

            override fun onHandleSuccess(t: List<UserInfo>) {
                mNumberItemLoaded = t.size
                callback?.getUserInfoListSuccess(mapDataWithDataBookMarked(t), false)
            }
        })
    }

    fun getUserBookMarkList() {
        val mappedList = bookMarkedHashMap.map {
            UserInfoModel(it.value.userId,
                    it.value.displayName, it.value.profileImage, it.value.reputation,
                    it.value.location, it.value.lastAccessDate, true)
        }
        callback?.getUserInfoListSuccess(mappedList, false)
    }

    fun loadMore() {
        ++page
        requestParams = GetUserListParams(page, MAX_ITEM_IN_PAGE, SITE)
        executeTask(mGetUserList, requestParams, object : BaseObserver<List<UserInfo>>() {
            override fun onHandleSuccess(t: List<UserInfo>) {
                mNumberItemLoaded = t.size
                callback?.getUserInfoListSuccess(mapDataWithDataBookMarked(t), true)
            }
        })
    }

    fun addToBookMarkList(model: UserInfoModel) {
        executeTask(mSaveUserBookMarked.get(), UserInfoModelMapper().revertMap(model)!!, object : BaseObserver<Any>() {
            override fun onBeforeEnd(isSuccess: Boolean) {
                if (isSuccess) {
                    bookMarkedHashMap[model.userId] = model
                    callback?.onUserBookMarked(model)
                }
            }
        })
    }

    fun removeFromBookMarkList(model: UserInfoModel) {
        executeTask(mRemoveUserBookMarked.get(), UserInfoModelMapper().revertMap(model)!!, object : BaseObserver<Any>() {
            override fun onBeforeEnd(isSuccess: Boolean) {
                if (isSuccess) {
                    bookMarkedHashMap.remove(model.userId)
                    callback?.onUserBookMarkRemoved(model)
                }
            }
        })
    }

    fun initUserBookMarkedList() {
        executeTask(mGetUserBookMarked, Any(), object : BaseObserver<List<UserInfo>>() {
            override fun onHandleSuccess(t: List<UserInfo>) {
                bookMarkedHashMap.apply {
                    clear()
                    UserInfoModelMapper().map(t).let {
                        for (item in it) {
                            put(item.userId, item)
                        }
                    }
                }
            }
        })
    }

    private fun mapDataWithDataBookMarked(dataList: List<UserInfo>): List<UserInfoModel> {
        val userInfoModelList = UserInfoModelMapper().map(dataList)
        if (dataList.isNotEmpty() and bookMarkedHashMap.isNotEmpty()) {
            for (data in userInfoModelList) {
                if (bookMarkedHashMap[data.userId] != null) {
                    data.isBookMarked = true
                }
            }
        }
        return userInfoModelList
    }

    val isLoadAll: Boolean
        get() = mNumberItemLoaded < MAX_ITEM_IN_PAGE
}