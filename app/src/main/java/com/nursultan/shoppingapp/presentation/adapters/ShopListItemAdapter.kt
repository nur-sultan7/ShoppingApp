package com.nursultan.shoppingapp.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.presentation.adapters.holders.ShopItemHolder
import com.nursultan.shoppingapp.presentation.adapters.utils.ShopItemDiffUtil
import java.lang.IllegalArgumentException

class ShopListItemAdapter : ListAdapter<ShopListItemDbModel, ShopItemHolder>(ShopItemDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemHolder {
        return when (viewType) {
            LIBRARY_ITEM -> {
                ShopItemHolder.create(parent, R.layout.item_list_name)
            }
            SHOP_ITEM -> {
                ShopItemHolder.create(parent, R.layout.item_list_name)
            }
            else -> {
                throw IllegalArgumentException("Unknown viewType: $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: ShopItemHolder, position: Int) {
        val item = getItem(position)
        if (item.ItemType == LIBRARY_ITEM) {
            holder.showShopItem(item)
        } else {
            holder.showLibraryItem(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).ItemType
    }

    companion object {
        const val LIBRARY_ITEM = 0
        const val SHOP_ITEM = 1
    }
}