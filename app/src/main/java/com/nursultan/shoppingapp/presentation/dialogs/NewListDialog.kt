package com.nursultan.shoppingapp.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nursultan.shoppingapp.databinding.DialogNewListBinding

object NewListDialog {
    lateinit var onSaveClickListener: (String) -> Unit
    fun showDialog(context: Context, setOnSaveClickListener: (String) -> Unit) {
        onSaveClickListener = setOnSaveClickListener
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = DialogNewListBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            btnCreateNewList.setOnClickListener {
                val listName = edNewListName.text.toString()
                if (listName.isNotEmpty()) {
                    onSaveClickListener.invoke(listName)
                }
                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
}