package com.nursultan.shoppingapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nursultan.shoppingapp.data.database.AppDatabase
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShoppingListNameDbModel
import kotlinx.coroutines.launch

class MainViewModel(db: AppDatabase) : ViewModel() {
    private val dao = db.getDao()
    val allNotes = dao.getAllNotes().asLiveData()
    val allShoppingListNames = dao.getAllShoppingListNames()
    fun insertNote(note: NoteItemDbModel) = viewModelScope.launch {
        dao.insertNote(note)
    }

    fun deleteNote(id: Int) = viewModelScope.launch {
        dao.deleteNote(id)
    }

    fun updateNote(note: NoteItemDbModel) = viewModelScope.launch {
        dao.updateNote(note)
    }

    fun insertShoppingList(slName: ShoppingListNameDbModel) = viewModelScope.launch {
        dao.insertShoppingListName(slName)
    }

    fun deleteShoppingList(id: Int) = viewModelScope.launch {
        dao.deleteShoppingListName(id)
    }

    fun updateShoppingListName(slName: ShoppingListNameDbModel) = viewModelScope.launch {
        dao.updateShoppingListName(slName)
    }
}