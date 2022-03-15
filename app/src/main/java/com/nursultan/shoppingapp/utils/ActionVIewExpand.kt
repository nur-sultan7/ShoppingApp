package com.nursultan.shoppingapp.utils

import android.content.Context
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ShopListActionView(private val context: AppCompatActivity, private val menuItem: MenuItem) :
    MenuItem.OnActionExpandListener {
    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        menuItem.isVisible = true
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        menuItem.isVisible = false
        context.invalidateOptionsMenu()
        return true
    }
}