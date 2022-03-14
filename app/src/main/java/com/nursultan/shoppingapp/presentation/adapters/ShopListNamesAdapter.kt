package com.nursultan.shoppingapp.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nursultan.shoppingapp.data.database.model.ShoppingListNameDbModel

class ShopListNamesAdapter :
    ListAdapter<ShoppingListNameDbModel, ShopListNameHolder>(ShopListNameDiffUtil) {
    var onDeleteClickListener: ((id: Int) -> Unit)? = null
    var onEditClickListener: ((ShoppingListNameDbModel)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListNameHolder {
        return ShopListNameHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ShopListNameHolder, position: Int) {
        val item = getItem(position)
        holder.setData(item)
        holder.itemView.setOnClickListener {

        }
        with(holder.binding)
        {
            imBtnDelete.setOnClickListener {
                onDeleteClickListener?.invoke(item.id)
            }
            imBtnEdit.setOnClickListener {
                onEditClickListener?.invoke(item)
            }
        }
    }
}