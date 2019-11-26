package com.thanht.stackoverflow.presentation.ui.base

interface BaseCallback {
    fun showLoading()

    fun hideLoading()

    fun showEmpty()

    fun hideEmpty()

    fun showError(message: String?)
}
