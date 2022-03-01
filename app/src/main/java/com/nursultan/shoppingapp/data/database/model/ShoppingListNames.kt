package com.nursultan.shoppingapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "shopping_list_names")
data class ShoppingListNames(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "allItemsCounter")
    val allItemsCounter: Int,
    @ColumnInfo(name = "checkedItemsCounter")
    val checkedItemsCounter: Int,
    @ColumnInfo(name = "itemsId")
    val itemsId: String
) : Serializable
