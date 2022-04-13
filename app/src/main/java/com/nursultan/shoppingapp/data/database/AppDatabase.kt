package com.nursultan.shoppingapp.data.database

import android.app.Application
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.nursultan.shoppingapp.data.database.model.LibraryItemDbModel
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel

@Database(
    entities = [LibraryItemDbModel::class, NoteItemDbModel::class,
        ShopListItemDbModel::class, ShopListNameItemDbModel::class],
    version = 8,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 7, to = 8, spec = AppDatabase.MigrationSpecs::class)]
)
abstract class AppDatabase : RoomDatabase() {
    @RenameColumn(
        tableName = "shopping_list_items",
        fromColumnName = "price",
        toColumnName = "item_price"
    )
    @RenameTable(fromTableName = "library", toTableName = "help")
    class MigrationSpecs : AutoMigrationSpec
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