package com.nursultan.shoppingapp.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel
import com.nursultan.shoppingapp.presentation.adapters.utils.ShopListNameDiffUtil

class ShopListNamesAdapter :
    ListAdapter<ShopListNameItemDbModel, ShopListNameHolder>(ShopListNameDiffUtil) {
    var onDeleteClickListener: ((id: Int) -> Unit)? = null
    var onEditClickListener: ((ShopListNameItemDbModel) -> Unit)? = null
    var onItemClickListener: ((ShopListNameItemDbModel) -> Unit)? = null

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
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
}