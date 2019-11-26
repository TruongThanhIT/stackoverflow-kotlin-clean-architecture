package com.thanht.stackoverflow.presentation.ui.util

import android.content.Context
import androidx.annotation.StringRes
import android.widget.Toast

object UIUtils {
    fun showShortToast(context: Context?, msg: CharSequence?) {
        if (context == null || msg == null || msg.isEmpty()) return
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showShortToast(context: Context?, @StringRes msg: Int) {
        if (context == null) return
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}
