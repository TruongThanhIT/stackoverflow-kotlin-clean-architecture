package com.thanht.stackoverflow.presentation.ui.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

import com.thanht.stackoverflow.R

class LoadingDialog(context: Context) : Dialog(context, R.style.DialogTransparent) {

    private val mUI: Handler = Handler(Looper.getMainLooper())
    private val mLoadingAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.anim_progress)
    private val mTvMessage: TextView
    private val mIvLoading: ImageView
    private var mDetached: Boolean = false

    private val DELAY_SHOW = Runnable {
        val context = getContext()
        if (context is Activity && context.isFinishing) {
            return@Runnable
        }
        super@LoadingDialog.show()
        mDetached = false
    }

    init {
        setContentView(R.layout.view_progress_dialog)
        mTvMessage = findViewById(R.id.opview_lab_title)
        mTvMessage.visibility = View.GONE

        mIvLoading = findViewById(R.id.loading_image)

        mIvLoading.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        setOperationCancelable(false)
    }

    fun close() {
        try {
            this.dismiss()
        } catch (e: Exception) {
            // not attach to window
            // fix based on http://stackoverflow.com/a/5102572/827110
        }

    }

    override fun onStart() {
        mIvLoading.startAnimation(mLoadingAnimation)
        super.onStart()
    }

    override fun onStop() {
        mIvLoading.clearAnimation()
        super.onStop()
    }

    fun setOperationCancelable(cancel: Boolean) {
        setCanceledOnTouchOutside(cancel)
        setCancelable(cancel)
    }

    fun setMessage(msg: String) {
        if (TextUtils.isEmpty(msg)) {
            mTvMessage.visibility = View.GONE
        } else {
            mTvMessage.text = msg
            mTvMessage.visibility = View.VISIBLE
        }
    }

    override fun show() {
        mUI.apply {
            removeCallbacks(DELAY_SHOW)
            post(DELAY_SHOW)
        }
    }

    override fun dismiss() {
        mUI.removeCallbacks(DELAY_SHOW)
        if (isShowing && !mDetached) {
            super.dismiss()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mDetached = true
        mUI.removeCallbacksAndMessages(null)
    }
}
