package com.nursultan.shoppingapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel
import com.nursultan.shoppingapp.databinding.ItemListNameBinding

class ShopListNameHolder(val binding: ItemListNameBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setData(shopListNameItem: ShopListNameItemDbModel) = with(binding)
    {
        tvListName.text = shopListNameItem.name
        tvTime.text = shopListNameItem.time
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