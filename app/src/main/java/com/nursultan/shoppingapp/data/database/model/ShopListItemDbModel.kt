package com.nursultan.shoppingapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_item")
data class ShopListItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val itemInfo: String?,
    val itemChecked: Int = 0,
    val itemListId: Int,
    val ItemType: String = "item"
)
