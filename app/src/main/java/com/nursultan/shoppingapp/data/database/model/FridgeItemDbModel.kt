package com.nursultan.shoppingapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "fridge_items")
data class FridgeItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val info: String = "",
    val type: Int = 0,
    val itemPrice: String = "not specified"
)