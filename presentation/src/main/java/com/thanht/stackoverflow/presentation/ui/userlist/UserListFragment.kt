package com.thanht.stackoverflow.presentation.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.thanht.stackoverflow.R
import com.thanht.stackoverflow.presentation.BaseApplication
import com.thanht.stackoverflow.presentation.model.UserInfoModel
import com.thanht.stackoverflow.presentation.ui.base.BaseFragment
import com.thanht.stackoverflow.presentation.ui.reputation.ReputationActivity
import com.thanht.stackoverflow.presentation.ui.userlist.adapter.UserListAdapter
import com.thanht.stackoverflow.presentation.ui.userlist.adapter.UserListAdapterListener
import com.thanht.stackoverflow.presentation.ui.util.UIUtils
import com.thanht.stackoverflow.presentation.ui.view.PagingCallback
import kotlinx.android.synthetic.main.fragment_user_list.*

const val TYPE_BOOK_MARK: Byte = 0
const val TYPE_ALL: Byte = 1

class UserListFragment : BaseFragment<UserListPresenter>(), UserListCallback,
        UserListAdapterListener, PagingCallback {

    private lateinit var mAdapter: UserListAdapter
    private var mSortPopup: PopupMenu? = null
    private var mCurrentSortType: Byte = TYPE_ALL

    override fun createPresenter(): UserListPresenter? = UserListPresenter(this).also {
        BaseApplication.instance.userComponent.inject(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter?.getUserList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvents()
        mPresenter?.initUserBookMarkedList()
    }

    override fun onDestroy() {
        mSortPopup?.dismiss()
        super.onDestroy()
    }

    override fun getUserInfoListSuccess(dataList: List<UserInfoModel>, isLoadMore: Boolean) {
        if (isLoadMore) {
            mAdapter.addMoreData(dataList)
        } else {
            mAdapter.loadData(dataList)
        }
    }

    override fun onItemSelected(position: Int) {
        mAdapter.getItem(position)?.let {
            ReputationActivity.navigate(activity, it.userId)
        }
    }

    override fun onBookMarkSelected(position: Int, isSelected: Boolean) {
        mAdapter.getItem(position)?.let {
            if (isSelected.not()) {
                mPresenter?.addToBookMarkList(it)
            } else {
                mPresenter?.removeFromBookMarkList(it)
            }
        }
    }

    override fun onUserBookMarkRemoved(userInfoModel: UserInfoModel) {
        if (mCurrentSortType == TYPE_BOOK_MARK) {
            mAdapter.removeItem(userInfoModel)
        } else {
            mAdapter.updateItemBookMarked(userInfoModel.userId, false)
        }
        UIUtils.showShortToast(activity, R.string.alert_remove_book_marked)
    }

    override fun onUserBookMarked(userInfoModel: UserInfoModel) {
        mAdapter.updateItemBookMarked(userInfoModel.userId, true)
        UIUtils.showShortToast(activity, R.string.alert_book_marked)
    }

    override val isLoadedAll: Boolean
        get() = mPresenter?.isLoadAll ?: true

    override fun onLoadMore() {
        if (mCurrentSortType != TYPE_BOOK_MARK) {
            mPresenter?.loadMore()
        }
    }

    private fun initEvents() {
        tv_sort.setOnClickListener {
            showPopUpMenu()
        }
    }

    private fun showPopUpMenu() {
        if (isResumed) {
            if (mSortPopup == null) {
                mSortPopup = PopupMenu(activity, tv_sort).apply {
                    menuInflater.inflate(R.menu.popup_menu, this.menu)
                    setOnMenuItemClickListener { item ->
                        tv_sort.text = item.title
                        if (item.itemId == R.id.book_marked) {
                            mCurrentSortType = TYPE_BOOK_MARK
                            mPresenter?.getUserBookMarkList()
                        } else {
                            mCurrentSortType = TYPE_ALL
                            mPresenter?.getUserList()
                        }
                        true
                    }
                }
            }
            mSortPopup!!.show()
        }
    }

    private fun initView() {
        mAdapter = UserListAdapter(this)
        recycler_view.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = mAdapter
            registerCallBack(this@UserListFragment)
            addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL).apply {
                setDrawable(ContextCompat.getDrawable(context, R.drawable.line_divider_1dp)!!)
            })
            (itemAnimator as androidx.recyclerview.widget.SimpleItemAnimator).supportsChangeAnimations = false
        }
    }
}