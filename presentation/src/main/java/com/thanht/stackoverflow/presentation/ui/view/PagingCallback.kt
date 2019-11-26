package com.thanht.stackoverflow.presentation.ui.view

interface PagingCallback {
    val isLoadedAll: Boolean
    fun onLoadMore()
}
