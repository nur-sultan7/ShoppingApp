package com.nursultan.shoppingapp.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nursultan.shoppingapp.data.database.model.ShoppingListNameDbModel

class ShopListNamesAdapter :
    ListAdapter<ShoppingListNameDbModel, ShopListNameHolder>(ShopListNameDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListNameHolder {
        return ShopListNameHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ShopListNameHolder, position: Int) {
        holder.setData(getItem(position))
        holder.itemView.setOnClickListener {

        }
        holder.binding.imBtnDelete.setOnClickListener {

        }
    }
}