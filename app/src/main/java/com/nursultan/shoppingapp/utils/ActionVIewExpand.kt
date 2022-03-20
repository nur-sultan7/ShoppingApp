package com.nursultan.shoppingapp.utils

import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ShopListActionView(
    private val context: AppCompatActivity,
    private val menuItem: MenuItem,

    ) :
    MenuItem.OnActionExpandListener {
    private lateinit var editText: EditText
    private lateinit var textWatcher: TextWatcher
    fun setEditTextWatcher(editText: EditText, textWatcher: TextWatcher) {
        this.editText = editText
        this.textWatcher = textWatcher
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        menuItem.isVisible = true
        editText.addTextChangedListener(textWatcher)
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        menuItem.isVisible = false
        editText.removeTextChangedListener(textWatcher)
        context.invalidateOptionsMenu()
        return true
    }
}