package com.thanht.stackoverflow.presentation.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.thanht.stackoverflow.presentation.ui.util.UIUtils
import com.thanht.stackoverflow.presentation.ui.view.LoadingDialog

open class BaseActivity : AppCompatActivity(), BaseCallback {
    private var mLoadingDialog: LoadingDialog? = null
    private var mCompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
        mLoadingDialog = null
    }

    protected fun injectFragment(containerViewId: Int, fragment: Fragment, tag : String = "") {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment, tag)
        fragmentTransaction.commit()
    }

    /*
    Handle base callback
     */
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
        UIUtils.showShortToast(this, message)
    }

    /*
    Support function
     */
    protected fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    private fun showLoading(msg: String, cancelable: Boolean) {
        if (isFinishing) return

        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(this)
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
