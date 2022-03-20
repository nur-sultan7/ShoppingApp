package com.nursultan.shoppingapp.utils

import android.text.Editable
import android.text.TextWatcher

abstract class OnTextChangeWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}