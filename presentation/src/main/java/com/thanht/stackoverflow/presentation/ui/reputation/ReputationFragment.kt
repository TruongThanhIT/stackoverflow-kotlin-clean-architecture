package com.thanht.stackoverflow.presentation.ui.reputation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.thanht.stackoverflow.R
import com.thanht.stackoverflow.presentation.BaseApplication
import com.thanht.stackoverflow.presentation.model.ReputationModel
import com.thanht.stackoverflow.presentation.ui.base.BaseFragment
import com.thanht.stackoverflow.presentation.ui.reputation.adapter.ReputationAdapter
import com.thanht.stackoverflow.presentation.ui.view.PagingCallback
import kotlinx.android.synthetic.main.fragment_reputation_history.*

class ReputationFragment : BaseFragment<ReputationHistoryPresenter>(), ReputationCallback,
        View.OnClickListener, PagingCallback {

    companion object {
        fun getInstance(userId: Int): ReputationFragment {
            return ReputationFragment().apply {
                arguments = Bundle().apply {
                    putInt(ReputationActivity.KEY_USER_ID, userId)
                }
            }
        }
    }

    private var mAdapter: ReputationAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reputation_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvents()
        mPresenter?.getReputationHistoryList(userId)
    }

    override fun loadReputationHistorySuccess(modelList: List<ReputationModel>, isLoadMore: Boolean) {
        if (isLoadMore) {
            mAdapter?.addMoreData(modelList)
        } else {
            mAdapter?.loadData(modelList)
        }
    }

    override fun createPresenter(): ReputationHistoryPresenter? = ReputationHistoryPresenter(this).also {
        BaseApplication.instance.userComponent.inject(it)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_nav) {
            if (isResumed) {
                activity!!.onBackPressed()
            }
        }
    }

    override val isLoadedAll: Boolean
        get() = mPresenter?.isLoadAll ?: true

    override fun onLoadMore() {
        mPresenter?.loadMore(userId)
    }

    private fun initView() {
        mAdapter = ReputationAdapter()
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            registerCallBack(this@ReputationFragment)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                setDrawable(ContextCompat.getDrawable(context, R.drawable.line_divider_1dp)!!)
            })
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        }
    }

    private fun initEvents() {
        btn_nav.setOnClickListener(this)
    }

    private val userId: Int
        get() = arguments!!.getInt(ReputationActivity.KEY_USER_ID)
}
