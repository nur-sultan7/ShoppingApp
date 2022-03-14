package com.nursultan.shoppingapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nursultan.shoppingapp.ShoppingApp
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel
import com.nursultan.shoppingapp.databinding.ActivityShopListBinding

class ShopListActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityShopListBinding.inflate(layoutInflater)
    }
    val viewModel: MainViewModel by viewModels {
        ViewModelFactory((application as ShoppingApp).appDatabase)
    }
    var shopListNameItem: ShopListNameItemDbModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        shopListNameItem = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItemDbModel
        binding.textView.text = shopListNameItem?.name
    }

    companion object {
        const val SHOP_LIST_NAME = "list_name"
    }

}