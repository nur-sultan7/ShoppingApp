package com.nursultan.shoppingapp.presentation.adapters.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.databinding.NoteListItemBinding
import com.nursultan.shoppingapp.utils.HtmlManager
import com.nursultan.shoppingapp.utils.TimeManager

class NoteViewHolder(val binding: NoteListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setData(note: NoteItemDbModel, timeFormat: String?) {
        with(binding)
        {
            tvTitle.text = note.title
            tvDescription.text = HtmlManager.getFromHtml(note.content)
            tvTime.text = TimeManager.getFormattedTime(note.time, timeFormat)
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