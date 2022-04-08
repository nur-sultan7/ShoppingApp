package com.nursultan.shoppingapp.utils

import android.icu.util.Calendar
import java.text.SimpleDateFormat
import java.util.*

object TimeManager {
    private const val DEFAULT_TIME_FORMAT = "hh:mm:ss - yyyy/MM/dd"
    fun getCurrentTime(): String {
        val currentTime = SimpleDateFormat(DEFAULT_TIME_FORMAT, Locale.getDefault())
        return currentTime.format(Calendar.getInstance().time)
    }

    fun getFormattedTime(time: String, timeFormat: String?): String {
        val defFormat = SimpleDateFormat(DEFAULT_TIME_FORMAT, Locale.getDefault())
        val defDate = defFormat.parse(time)

        val newFormatter = SimpleDateFormat(timeFormat, Locale.getDefault())
        return if (defDate != null)
            newFormatter.format(defDate)
        else
            time
    }
}