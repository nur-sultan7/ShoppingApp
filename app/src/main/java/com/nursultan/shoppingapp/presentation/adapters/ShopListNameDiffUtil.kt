package com.nursultan.shoppingapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel

object ShopListNameDiffUtil: DiffUtil.ItemCallback<ShopListNameItemDbModel>() {
    override fun areItemsTheSame(
        oldItem: ShopListNameItemDbModel,
        newItem: ShopListNameItemDbModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShopListNameItemDbModel,
        newItem: ShopListNameItemDbModel
    ): Boolean {
        return oldItem == newItem
    }
}