package com.nursultan.shoppingapp.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel

class NotesListAdapter : ListAdapter<NoteItemDbModel, NoteViewHolder>(NoteDiffUtil) {

    lateinit var setOnDeleteListener: (id: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.setData(item)
        holder.binding.ivDelete.setOnClickListener {
            setOnDeleteListener(item.id)
        }
    }
}