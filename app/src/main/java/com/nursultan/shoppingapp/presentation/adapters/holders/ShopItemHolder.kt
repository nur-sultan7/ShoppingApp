package com.nursultan.shoppingapp.presentation.adapters.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.databinding.ItemLibraryShopListBinding
import com.nursultan.shoppingapp.databinding.ItemShopListBinding
import com.nursultan.shoppingapp.utils.VisibilitySetter

class ShopItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private var binding: ViewBinding? = null
    fun showShopItem(item: ShopListItemDbModel) {
        val binding = ItemShopListBinding.bind(view)
        binding.apply {
            tvName.text = item.name
            tvInfo.text = item.info
            tvInfo.visibility = VisibilitySetter.setVisibilityByString(item.info)
        }
    }

    fun showLibraryItem(item: ShopListItemDbModel) {
        binding = ItemLibraryShopListBinding.bind(view)
    }



    companion object {
        fun create(parent: ViewGroup, layoutId: Int): ShopItemHolder {
            return ShopItemHolder(
                LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            )
        }
    }
}