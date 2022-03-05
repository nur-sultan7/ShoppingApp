package com.nursultan.shoppingapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nursultan.shoppingapp.data.database.AppDatabase
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import kotlinx.coroutines.launch

class MainViewModel(db: AppDatabase) : ViewModel() {
    private val dao = db.getDao()
    val allNotes = dao.getAllNotes().asLiveData()
    fun insertNote(note: NoteItemDbModel) = viewModelScope.launch {
        dao.insertNote(note)
    }
}