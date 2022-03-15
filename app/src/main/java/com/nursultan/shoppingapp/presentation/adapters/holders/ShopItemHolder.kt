package com.nursultan.shoppingapp.presentation.adapters.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel

class ShopItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun showShopItem(item: ShopListItemDbModel) {

    }

    fun showLibraryItem(item: ShopListItemDbModel) {

    }

    companion object {
        fun create(parent: ViewGroup, layoutId: Int): ShopItemHolder {
            return ShopItemHolder(
                LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            )
        }
    }
}