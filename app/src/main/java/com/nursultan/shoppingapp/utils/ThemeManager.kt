package com.nursultan.shoppingapp.utils

import android.content.SharedPreferences
import com.nursultan.shoppingapp.R

object ThemeManager {
    fun getTheme(defPref: SharedPreferences): Int {
        return if (defPref.getString("theme_style_preference", null) == "Green")
            R.style.Theme_ShoppingAppGreen
        else
            R.style.Theme_ShoppingAppBlack
    }
}