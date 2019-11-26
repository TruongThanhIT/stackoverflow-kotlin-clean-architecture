package com.thanht.stackoverflow.presentation.ui.userlist.adapter

interface UserListAdapterListener {
    fun onItemSelected(position: Int)
    fun onBookMarkSelected(position: Int, isSelected: Boolean)
}