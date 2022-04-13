package com.nursultan.shoppingapp.data.database

import android.app.Application
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nursultan.shoppingapp.data.database.model.LibraryItemDbModel
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel

@Database(
    entities = [LibraryItemDbModel::class, NoteItemDbModel::class,
        ShopListItemDbModel::class, ShopListNameItemDbModel::class],
    version = 7,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 6, to = 7)]
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