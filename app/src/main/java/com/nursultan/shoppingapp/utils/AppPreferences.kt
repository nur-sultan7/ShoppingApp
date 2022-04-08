package com.nursultan.shoppingapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.nursultan.shoppingapp.R

class AppPreferences(private val context: Context) {
    val preferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getSelectedThemeId(): Int {
        return if (getSelectedTheme() == "Green")
            R.style.Theme_ShoppingAppGreen
        else
            R.style.Theme_ShoppingAppBlack
    }

    fun getSelectedTheme() = preferences.getString(context.getString(R.string.pref_key_theme), null)
}