package com.nursultan.shoppingapp.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nursultan.shoppingapp.databinding.DialogDeleteShoppingListNameBinding

object DeleteShopListNameDialog {
    fun show(context: Context,  onDeleteClick: ()->Unit) {
        var deleteDialog: AlertDialog? = null
        val dialogBuilder = AlertDialog.Builder(context)
        val binding = DialogDeleteShoppingListNameBinding.inflate(LayoutInflater.from(context))
        dialogBuilder.setView(binding.root)
        binding.btnDelete.setOnClickListener {
            onDeleteClick.invoke()
            deleteDialog?.dismiss()
        }
        binding.btnCancel.setOnClickListener {
            deleteDialog?.dismiss()
        }
        deleteDialog = dialogBuilder.create()
        deleteDialog.window?.setBackgroundDrawable(null)
        deleteDialog.show()
    }
}