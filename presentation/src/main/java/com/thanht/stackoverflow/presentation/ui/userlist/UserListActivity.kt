package com.thanht.stackoverflow.presentation.ui.userlist

import android.os.Bundle
import com.thanht.stackoverflow.R
import com.thanht.stackoverflow.presentation.ui.base.BaseActivity

class UserListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contain_fragment)

        if (savedInstanceState == null) {
            injectFragment(R.id.fragmentContainer, UserListFragment())
        }
    }
}