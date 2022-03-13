package com.nursultan.shoppingapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nursultan.shoppingapp.data.database.model.ShoppingListNameDbModel
import com.nursultan.shoppingapp.databinding.ItemListNameBinding

class ShopListNameHolder(val binding: ItemListNameBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setData(shoppingListName: ShoppingListNameDbModel) = with(binding)
    {
        tvListName.text = shoppingListName.name
        tvTime.text = shoppingListName.time
    }

    companion object {
        fun create(parent: ViewGroup): ShopListNameHolder {
            val binding = ItemListNameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShopListNameHolder(binding)
        }
    }
}