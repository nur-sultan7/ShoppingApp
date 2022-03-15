package com.nursultan.shoppingapp.presentation.adapters.utils

import androidx.recyclerview.widget.DiffUtil
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel

object ShopItemDiffUtil : DiffUtil.ItemCallback<ShopListItemDbModel>() {
    override fun areItemsTheSame(
        oldItem: ShopListItemDbModel,
        newItem: ShopListItemDbModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShopListItemDbModel,
        newItem: ShopListItemDbModel
    ): Boolean {
        return oldItem == newItem
    }
}