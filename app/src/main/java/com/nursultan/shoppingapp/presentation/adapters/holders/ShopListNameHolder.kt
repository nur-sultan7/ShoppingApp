package com.nursultan.shoppingapp.presentation.adapters.holders

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel
import com.nursultan.shoppingapp.databinding.ItemListNameBinding

class ShopListNameHolder(val binding: ItemListNameBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun setData(itemShopName: ShopListNameItemDbModel) = with(binding)
    {
        tvCounter.text = "${itemShopName.checkedItemsCounter}/${itemShopName.allItemsCounter}"
        tvListName.text = itemShopName.name
        tvTime.text = itemShopName.time
        progressBar.max = itemShopName.allItemsCounter
        progressBar.progress = itemShopName.checkedItemsCounter
        val colorState = ColorStateList.valueOf(getColorByState(itemShopName, root.context))
        progressBar.progressTintList = colorState
        cardCounter.backgroundTintList = colorState
    }

    private fun getColorByState(item: ShopListNameItemDbModel, context: Context): Int {
        return if (item.allItemsCounter == item.checkedItemsCounter) {
            ContextCompat.getColor(context, R.color.main_green)
        } else {
            ContextCompat.getColor(context, R.color.picker_red)
        }
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