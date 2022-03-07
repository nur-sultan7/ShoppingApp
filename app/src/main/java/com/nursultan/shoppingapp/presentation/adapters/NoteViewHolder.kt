package com.nursultan.shoppingapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.databinding.NoteListItemBinding

class NoteViewHolder(val binding: NoteListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setData(note: NoteItemDbModel) {
        with(binding)
        {
            tvTitle.text = note.title
            tvDescription.text = note.content
            tvTime.text = note.time
        }
    }

    companion object {
        fun create(view: ViewGroup): NoteViewHolder {
            val viewBinding = NoteListItemBinding.inflate(
                LayoutInflater.from(view.context),
                view,
                false
            )
            return NoteViewHolder(viewBinding)
        }
    }
}