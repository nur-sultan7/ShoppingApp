package com.nursultan.shoppingapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShoppingListNameDbModel
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
    suspend fun insertShoppingListName(listName: ShoppingListNameDbModel)

    @Query("select * from shopping_list_names")
    fun getAllShoppingListNames(): LiveData<List<ShoppingListNameDbModel>>

    @Query("delete from shopping_list_names where id is :id")
    suspend fun deleteShoppingListName(id: Int)
}