package com.nursultan.shoppingapp

import android.app.Application
import com.nursultan.shoppingapp.data.database.AppDatabase

class ShoppingApp : Application() {
    val appDatabase by lazy {
        AppDatabase.getInstance(this)
    }
}