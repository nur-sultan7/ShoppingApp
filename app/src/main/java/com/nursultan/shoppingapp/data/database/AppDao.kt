package com.nursultan.shoppingapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nursultan.shoppingapp.data.database.model.LibraryItemDbModel
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {
    //note queries
    @Insert
    suspend fun insertNote(note: NoteItemDbModel)

    @Query("select * from note_list")
    fun getAllNotes(): Flow<List<NoteItemDbModel>>

    @Query("delete from note_list where id is :id")
    suspend fun deleteNote(id: Int)

    @Update
    suspend fun updateNote(note: NoteItemDbModel)

    //shopping list name Queries
    @Insert
    suspend fun insertShoppingListName(listNameItem: ShopListNameItemDbModel)

    @Query("select * from shopping_list_names")
    fun getAllShoppingListNames(): LiveData<List<ShopListNameItemDbModel>>

    @Query("delete from shopping_list_names where id is :id")
    suspend fun deleteShoppingListName(id: Int)

    @Update
    suspend fun updateShoppingListName(shopListNameItem: ShopListNameItemDbModel)

    //ShopListItem queries
    @Insert
    suspend fun insertShopListItem(shopListItemDbModel: ShopListItemDbModel)

    @Query("select * from shopping_list_items where listId is :listId")
    fun getAllShopListItems(listId: Int): LiveData<List<ShopListItemDbModel>>

    @Update
    suspend fun updateShopListItem(item: ShopListItemDbModel)

    @Query("delete from shopping_list_items where listId is :listId")
    suspend fun deleteShopListItems(listId: Int)

    //LibraryItem queries
    @Insert
    suspend fun insertLibraryItem(libraryItemDbModel: LibraryItemDbModel)

    @Query("select exists (select * from library where name like :itemName)")
    suspend fun isExistLibraryItem(itemName: String): Boolean

    @Query("select * from library where name like :name")
    suspend fun getLibraryItems(name: String): List<LibraryItemDbModel>

    @Update
    suspend fun updateLibraryItem(item: LibraryItemDbModel)

    @Query("delete from library where id is :id")
    suspend fun deleteLibraryItem(id: Int)
}