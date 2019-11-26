package com.thanht.stackoverflow.presentation.ui.reputation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.thanht.stackoverflow.R
import com.thanht.stackoverflow.presentation.ui.base.BaseActivity

class ReputationActivity : BaseActivity() {

    companion object {
        const val KEY_USER_ID = "user_id"

        fun navigate(activity: Activity?, userId: Int) {
            activity?.let {
                val intent = Intent(it, ReputationActivity::class.java).apply {
                    putExtra(KEY_USER_ID, userId)
                }
                it.startActivity(intent)
            }
        }
    }

    private var mReputationHistoryFragment: ReputationFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contain_fragment)

        if (savedInstanceState == null) {
            mReputationHistoryFragment = ReputationFragment.getInstance(intent.getIntExtra(KEY_USER_ID, -1))
            injectFragment(R.id.fragmentContainer, mReputationHistoryFragment!!, "ReputationFragment")
        } else {
            mReputationHistoryFragment = supportFragmentManager.findFragmentByTag("ReputationFragment") as? ReputationFragment
        }
    }
}
