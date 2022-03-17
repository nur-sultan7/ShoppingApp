package com.nursultan.shoppingapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_items")
data class ShopListItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val info: String? = null,
    val checked: Int = 0,
    val listId: Int,
    val type: Int = 0
)
