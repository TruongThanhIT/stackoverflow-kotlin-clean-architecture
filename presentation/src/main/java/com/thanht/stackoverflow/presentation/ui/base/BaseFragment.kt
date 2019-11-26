package com.thanht.stackoverflow.presentation.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.thanht.stackoverflow.presentation.ui.util.UIUtils
import com.thanht.stackoverflow.presentation.ui.view.LoadingDialog

abstract class BaseFragment<V : BasePresenter<*>> : Fragment(), BaseCallback {
    protected var mPresenter: V? = null
    private var mLoadingDialog: LoadingDialog? = null

    init {
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
        mPresenter?.create()
    }

    protected abstract fun createPresenter(): V?

    override fun onStart() {
        super.onStart()
        mPresenter?.start()
    }

    override fun onPause() {
        super.onPause()
        mPresenter?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.destroy()
        mLoadingDialog = null
    }

    override fun showLoading() {
        showLoading("", false)
    }

    override fun hideLoading() {
        mLoadingDialog?.close()
    }

    override fun showEmpty() {
    }

    override fun hideEmpty() {
    }

    override fun showError(message: String?) {
        UIUtils.showShortToast(activity, message)
    }

    private fun showLoading(msg: String, cancelable: Boolean) {
        if (activity?.isFinishing == true) return

        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(activity!!)
        } else {
            if (mLoadingDialog?.isShowing == true) {
                mLoadingDialog?.setMessage(msg)
                return
            }
        }

        mLoadingDialog?.setMessage(msg)
        mLoadingDialog?.setOperationCancelable(cancelable)
        mLoadingDialog?.show()
    }
}
