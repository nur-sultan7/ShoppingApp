package com.nursultan.shoppingapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel

object NoteDiffUtil : DiffUtil.ItemCallback<NoteItemDbModel>() {
    override fun areItemsTheSame(oldItem: NoteItemDbModel, newItem: NoteItemDbModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteItemDbModel, newItem: NoteItemDbModel): Boolean {
        return oldItem == newItem
    }
}