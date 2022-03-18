package com.nursultan.shoppingapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nursultan.shoppingapp.data.database.AppDatabase
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel
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

    fun insertShoppingList(slNameItem: ShopListNameItemDbModel) = viewModelScope.launch {
        dao.insertShoppingListName(slNameItem)
    }

    fun deleteShoppingList(id: Int) = viewModelScope.launch {
        dao.deleteShoppingListName(id)
    }

    fun updateShoppingListName(slNameItem: ShopListNameItemDbModel) = viewModelScope.launch {
        dao.updateShoppingListName(slNameItem)
    }

    fun insertShopListItem(shopListItemDbModel: ShopListItemDbModel) = viewModelScope.launch {
        dao.insertShopListItem(shopListItemDbModel)
    }

    fun getAllShoppingListItems(shopListId: Int): LiveData<List<ShopListItemDbModel>> {
        return dao.getAllShopListItems(shopListId)
    }
    fun updateShopListItem(item: ShopListItemDbModel) = viewModelScope.launch {
        dao.updateShopListItem(item)
    }
}