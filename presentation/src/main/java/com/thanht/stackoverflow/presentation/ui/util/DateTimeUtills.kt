package com.thanht.stackoverflow.presentation.ui.util

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

private val DATE_TIME_FORMAT = SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.ENGLISH)
private val TIME_FORMAT = SimpleDateFormat("HH:mm", Locale.ENGLISH)

fun getDateTime(millis: Long): String {
    return if (DateUtils.isToday(millis)) {
        TIME_FORMAT.format(millis)
    } else DATE_TIME_FORMAT.format(millis)
}