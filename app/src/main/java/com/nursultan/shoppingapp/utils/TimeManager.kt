package com.nursultan.shoppingapp.utils

import android.icu.util.Calendar
import java.text.SimpleDateFormat
import java.util.*

object TimeManager {
    fun getCurrentTime(): String {
        val currentTime = SimpleDateFormat("hh:mm:ss - yyyy/MM/dd", Locale.getDefault())
        return currentTime.format(Calendar.getInstance().time)
    }
}