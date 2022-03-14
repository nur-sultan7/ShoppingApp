package com.nursultan.shoppingapp.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.databinding.DialogNewListBinding
import java.lang.StringBuilder

object NewListDialog {
    fun showDialog(
        context: Context,
        currentListName: String? = null,
        onSaveClickListener: (String) -> Unit
    ) {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = DialogNewListBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            if (currentListName != null) {
                btnCreateNewList.text = context.getText(R.string.update)
                tvTitle.text = context.getText(R.string.change_list_name_title)
                edNewListName.setText(currentListName)
            }
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