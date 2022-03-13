package com.nursultan.shoppingapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.nursultan.shoppingapp.data.database.model.ShoppingListNameDbModel

object ShopListNameDiffUtil: DiffUtil.ItemCallback<ShoppingListNameDbModel>() {
    override fun areItemsTheSame(
        oldItem: ShoppingListNameDbModel,
        newItem: ShoppingListNameDbModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShoppingListNameDbModel,
        newItem: ShoppingListNameDbModel
    ): Boolean {
        return oldItem == newItem
    }
}