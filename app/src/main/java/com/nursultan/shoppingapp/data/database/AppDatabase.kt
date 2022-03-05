package com.nursultan.shoppingapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nursultan.shoppingapp.data.database.model.LibraryItemDbModel
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShoppingListItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShoppingListNamesDbModel

@Database(
    entities = [LibraryItemDbModel::class, NoteItemDbModel::class,
        ShoppingListItemDbModel::class, ShoppingListNamesDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(application: Application): AppDatabase {
            return INSTANCE ?: synchronized(this)
            {
                INSTANCE?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    "app_db.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getDao(): AppDao
}