package com.nursultan.shoppingapp.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.databinding.DialogEditListItemBinding

object EditListItemDialog {
    fun showDialog(
        context: Context,
        shopListItem: ShopListItemDbModel,
        onEditListener: (item: ShopListItemDbModel) -> Unit
    ) {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = DialogEditListItemBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        with(binding)
        {
            edListItemName.setText(shopListItem.name)
            edListItemInfo.setText(shopListItem.info)
            if (shopListItem.type==1)
            {
                edListItemInfo.visibility = View.GONE
            }
            btnUpdate.setOnClickListener {
                onEditListener.invoke(
                    shopListItem.copy(
                        name = edListItemName.text.toString(),
                        info = edListItemInfo.text.toString()
                    )
                )
                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
}