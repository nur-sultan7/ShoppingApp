package com.nursultan.shoppingapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_list_items",
    foreignKeys = [ForeignKey(
        entity = ShopListNameItemDbModel::class,
        parentColumns = ["id"],
        childColumns = ["listId"],
        onDelete = CASCADE
    )]
)
data class ShopListItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val info: String = "",
    val checked: Boolean = false,
    val listId: Int,
    val type: Int = 0,
    @ColumnInfo(defaultValue = "not specified")
    val price: String = "not specified"
)
