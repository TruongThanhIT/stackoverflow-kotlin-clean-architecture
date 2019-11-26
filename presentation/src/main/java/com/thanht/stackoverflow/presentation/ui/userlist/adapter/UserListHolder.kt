package com.thanht.stackoverflow.presentation.ui.userlist.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thanht.stackoverflow.R
import com.thanht.stackoverflow.presentation.model.UserInfoModel
import com.thanht.stackoverflow.presentation.ui.util.getDateTime
import java.lang.ref.WeakReference

class UserListHolder(itemView: View, listener: UserListAdapterListener) : RecyclerView.ViewHolder(itemView) {
    private val mTvName = itemView.findViewById<TextView>(R.id.tv_name)
    private val mTvLocation = itemView.findViewById<TextView>(R.id.tv_location)
    private val mTvReputation = itemView.findViewById<TextView>(R.id.tv_reputation)
    private val mTvLastAccessDate = itemView.findViewById<TextView>(R.id.tv_date)
    private val mIvAvatar = itemView.findViewById<ImageView>(R.id.iv_avatar)
    private val mIvBookMark = itemView.findViewById<ImageView>(R.id.iv_book_mark)

    private val mWeakReference: WeakReference<UserListAdapterListener> = WeakReference(listener)

    init {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                mWeakReference.get()?.onItemSelected(adapterPosition)
            }
        }

        mIvBookMark.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                mWeakReference.get()?.onBookMarkSelected(adapterPosition, it.isSelected)
            }
        }
    }

    fun bindData(model: UserInfoModel?) {
        model?.let {
            mTvName.text = it.displayName
            mTvLocation.text = it.location
            mTvReputation.text = String.format("%d Reputation", it.reputation)
            mTvLastAccessDate.text = String.format("Last seen %s", getDateTime(it.lastAccessDate.times(1000)))
            mIvBookMark.isSelected = it.isBookMarked
            val size = itemView.context.resources.getDimensionPixelSize(R.dimen.dp_50)
            Glide.with(itemView.context)
                    .load(it.profileImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_user)
                    .override(size, size)
                    .circleCrop()
                    .autoClone()
                    .into(mIvAvatar)
        }
    }
}