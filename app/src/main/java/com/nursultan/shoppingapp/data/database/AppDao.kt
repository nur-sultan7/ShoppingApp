package com.nursultan.shoppingapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {
    //Queries with note
    @Insert
    suspend fun insertNote(note: NoteItemDbModel)

    @Query("select * from note_list")
    fun getAllNotes(): Flow<List<NoteItemDbModel>>

    @Query("delete from note_list where id is :id")
    suspend fun deleteNote(id: Int)

    @Update
    suspend fun updateNote(note: NoteItemDbModel)

    //Queries with shopping list name
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
}