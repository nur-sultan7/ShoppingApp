package com.nursultan.shoppingapp.utils

import android.view.View

object VisibilitySetter {
    fun setVisibilityByString(text: String?): Int {
        return if (text.isNullOrEmpty())
            View.GONE
        else
            View.VISIBLE
    }

    fun <T> setVisibilityByList(list: List<T>): Int {
        return if (list.isNullOrEmpty())
            View.VISIBLE
        else
            View.GONE
    }
}