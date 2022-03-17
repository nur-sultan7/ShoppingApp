package com.nursultan.shoppingapp.utils

import android.view.View
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel

object VisibilitySetter {
    fun setVisibilityByString(text: String?): Int {
        return if (text.isNullOrEmpty())
            View.VISIBLE
        else
            View.GONE
    }

    fun setVisibilityByList(list: List<ShopListItemDbModel>): Int {
        return if (list.isNullOrEmpty())
            View.VISIBLE
        else
            View.GONE
    }
}