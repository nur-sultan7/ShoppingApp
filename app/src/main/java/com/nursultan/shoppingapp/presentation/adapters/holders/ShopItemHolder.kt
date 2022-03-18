package com.nursultan.shoppingapp.presentation.adapters.holders

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.databinding.ItemLibraryShopListBinding
import com.nursultan.shoppingapp.databinding.ItemShopListBinding
import com.nursultan.shoppingapp.utils.VisibilitySetter

class ShopItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var binding: ViewBinding?=null
    fun showShopItem(item: ShopListItemDbModel) {
        val binding = ItemShopListBinding.bind(view)
        binding.apply {
            tvName.text = item.name
            tvInfo.text = item.info
            checkBox.isChecked = item.checked
            tvInfo.visibility = VisibilitySetter.setVisibilityByString(item.info)
            setPaintFlagAndColor(binding)
        }
        this.binding = binding
    }

    fun showLibraryItem(item: ShopListItemDbModel) {
       // val binding = ItemLibraryShopListBinding.bind(view)
    }

    private fun setPaintFlagAndColor(binding: ItemShopListBinding) {
        binding.apply {
            if (checkBox.isChecked) {
                tvName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tvInfo.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tvName.setTextColor(binding.root.context.getColor(R.color.grey))
                tvInfo.setTextColor(binding.root.context.getColor(R.color.grey))
            } else {
                tvName.paintFlags = Paint.ANTI_ALIAS_FLAG
                tvInfo.paintFlags = Paint.ANTI_ALIAS_FLAG
                tvName.setTextColor(binding.root.context.getColor(R.color.black))
                tvInfo.setTextColor(binding.root.context.getColor(R.color.black))
            }
        }
    }


    companion object {
        fun create(parent: ViewGroup, layoutId: Int): ShopItemHolder {
            return ShopItemHolder(
                LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            )
        }
    }
}