package com.nursultan.shoppingapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {
    @Insert
    suspend fun insertNote(note: NoteItemDbModel)

    @Query("select * from note_list")
    fun getAllNotes(): Flow<List<NoteItemDbModel>>

    @Query("delete from note_list where id is :id")
    suspend fun deleteNote(id: Int)
}