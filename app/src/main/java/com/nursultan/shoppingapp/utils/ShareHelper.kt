package com.nursultan.shoppingapp.utils

import android.content.Intent
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel

object ShareHelper {
    fun shareShopList(shopList: List<ShopListItemDbModel>, shopListName: String): Intent {
        return Intent(Intent.ACTION_SEND).apply {
            type = "text/plane"
            putExtra(Intent.EXTRA_TEXT, makeShareText(shopList, shopListName))
        }
    }

    private fun makeShareText(shopList: List<ShopListItemDbModel>, shopListName: String): String {
        val shareText = StringBuilder()
        shareText.append("$shopListName: \n")
        for ((i, v) in shopList.withIndex()) {
            shareText.append("${i + 1} - ${v.name} (${v.info})\n")
        }
        return shareText.toString()
    }
}