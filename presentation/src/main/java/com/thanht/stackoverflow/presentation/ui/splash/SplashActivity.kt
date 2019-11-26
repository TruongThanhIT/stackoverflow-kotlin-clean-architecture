package com.thanht.stackoverflow.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.thanht.stackoverflow.presentation.ui.base.BaseActivity
import com.thanht.stackoverflow.presentation.ui.userlist.UserListActivity

class SplashActivity : BaseActivity() {
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mHandler = Handler()
        mHandler?.postDelayed({
            startActivity(Intent(this, UserListActivity::class.java))
            overridePendingTransition(0, 0)
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler?.removeCallbacksAndMessages(null)
    }
}
