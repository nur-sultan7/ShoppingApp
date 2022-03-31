package com.nursultan.shoppingapp.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.presentation.adapters.holders.NoteViewHolder
import com.nursultan.shoppingapp.presentation.adapters.utils.NoteDiffUtil

class NotesListAdapter(private val timeFormat: String?) : ListAdapter<NoteItemDbModel, NoteViewHolder>(NoteDiffUtil) {

    lateinit var setOnDeleteListener: (id: Int) -> Unit
    lateinit var setOnItemClickListener: (note: NoteItemDbModel) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.setData(item, timeFormat)
        holder.binding.ivDelete.setOnClickListener {
            setOnDeleteListener(item.id)
        }
        holder.binding.root.setOnClickListener {
            setOnItemClickListener.invoke(item)
        }
    }
}