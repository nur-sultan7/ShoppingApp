package com.nursultan.shoppingapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nursultan.shoppingapp.data.database.model.*

@Database(
    entities = [LibraryItemDbModel::class, NoteItemDbModel::class,
        ShopListItemDbModel::class, ShopListNameItemDbModel::class,
        FridgeItemDbModel::class],
    version = 9,
    exportSchema = false,
//    autoMigrations = [AutoMigration(from = 7, to = 8, spec = AppDatabase.MigrationSpecs::class)]
)
abstract class AppDatabase : RoomDatabase() {
    //    @RenameColumn(
//        tableName = "shopping_list_items",
//        fromColumnName = "price",
//        toColumnName = "item_price"
//    )
//    @RenameTable(fromTableName = "library", toTableName = "help")
//    class MigrationSpecs : AutoMigrationSpec
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
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getDao(): AppDao
}