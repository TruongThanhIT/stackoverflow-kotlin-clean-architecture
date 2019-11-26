package com.thanht.stackoverflow.presentation.ui.view

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet

class PagingRecyclerView : RecyclerView {
    private var mCallback: PagingCallback? = null
    private var mThresholdToTrigger = 5

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val lastVisibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val totalItem = adapter?.itemCount ?: 0

                    if (totalItem <= lastVisibleItem + mThresholdToTrigger) {
                        if (mCallback != null && !mCallback!!.isLoadedAll) {
                            mCallback!!.onLoadMore()
                        }
                    }
                }
            }
        })
    }

    fun registerCallBack(callback: PagingCallback) {
        mCallback = callback
    }

    fun setThresholdToTrigger(thresholdToTrigger: Int) {
        this.mThresholdToTrigger = thresholdToTrigger
    }
}
