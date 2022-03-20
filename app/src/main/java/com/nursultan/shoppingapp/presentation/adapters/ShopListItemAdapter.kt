package com.nursultan.shoppingapp.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.databinding.ItemLibraryShopListBinding
import com.nursultan.shoppingapp.databinding.ItemShopListBinding
import com.nursultan.shoppingapp.presentation.adapters.holders.ShopItemHolder
import com.nursultan.shoppingapp.presentation.adapters.utils.ShopItemDiffUtil
import java.lang.IllegalArgumentException

class ShopListItemAdapter : ListAdapter<ShopListItemDbModel, ShopItemHolder>(ShopItemDiffUtil) {
    var onCheckClickListener: ((ShopListItemDbModel) -> Unit)? = null
    var onEditClickListener: ((ShopListItemDbModel) -> Unit)? = null
    var onLibraryEditClickListener: ((ShopListItemDbModel) -> Unit)? = null
    var onLibraryDeleteClickListener: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemHolder {
        return when (viewType) {
            LIBRARY_ITEM -> {
                ShopItemHolder.create(parent, R.layout.item_library_shop_list)
            }
            SHOP_ITEM -> {
                ShopItemHolder.create(parent, R.layout.item_shop_list)
            }
            else -> {
                throw IllegalArgumentException("Unknown viewType: $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: ShopItemHolder, position: Int) {
        val item = getItem(position)
        if (item.type == SHOP_ITEM) {
            holder.showShopItem(item)
            with(holder.binding as ItemShopListBinding)
            {
                checkBox.setOnClickListener {
                    onCheckClickListener?.invoke(item.copy(checked = checkBox.isChecked))
                }
                imBtnEdit.setOnClickListener {
                    onEditClickListener?.invoke(item)
                }
            }
        } else {
            holder.showLibraryItem(item)
            with(holder.binding as ItemLibraryShopListBinding)
            {
                imBtnEditShopItem.setOnClickListener {
                    onLibraryEditClickListener?.invoke(item)
                }
                imBtnDelete.setOnClickListener {
                    onLibraryDeleteClickListener?.invoke(item.id)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    companion object {
        const val LIBRARY_ITEM = 1
        const val SHOP_ITEM = 0
    }
}