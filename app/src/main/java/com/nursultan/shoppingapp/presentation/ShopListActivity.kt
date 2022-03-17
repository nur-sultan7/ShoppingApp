package com.nursultan.shoppingapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.ShoppingApp
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel
import com.nursultan.shoppingapp.databinding.ActivityShopListBinding
import com.nursultan.shoppingapp.presentation.adapters.ShopListItemAdapter
import com.nursultan.shoppingapp.utils.VisibilitySetter
import com.nursultan.shoppingapp.utils.ShopListActionView

class ShopListActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityShopListBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory((application as ShoppingApp).appDatabase)
    }
    var shopListNameItem: ShopListNameItemDbModel? = null
    private lateinit var edItemName: EditText
    private lateinit var adapter: ShopListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        initViews()
        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shop_list_menu, menu)
        menu?.let {
            val saveItem = menu.findItem(R.id.save_item)
            saveItem.isVisible = false
            val addItem = menu.findItem(R.id.add_item)
            addItem.setOnActionExpandListener(ShopListActionView(this, saveItem))
            edItemName = addItem.actionView.findViewById(R.id.edShopListItem)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_item) {
            setOnMenuSaveItemListener()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setOnMenuSaveItemListener() {
        if (edItemName.text.toString().isNotEmpty()) {
            viewModel.insertShopListItem(
                ShopListItemDbModel(
                    name = edItemName.text.toString(),
                    listId = shopListNameItem?.id ?: return
                )
            )
            edItemName.text = null
        }
    }

    private fun init() {
        shopListNameItem = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItemDbModel
    }

    private fun initViews() = with(binding) {
        rcViewShopListItems.layoutManager = LinearLayoutManager(this@ShopListActivity)
        adapter = ShopListItemAdapter()
        rcViewShopListItems.adapter = adapter
    }

    private fun setObservers() {
        shopListNameItem?.let {
            viewModel.getAllShoppingListItems(it.id).observe(this) { list ->
                adapter.submitList(list)
                binding.tvEmptyList.visibility = VisibilitySetter.setVisibilityByList(list)
            }
        }
    }

    companion object {
        const val SHOP_LIST_NAME = "list_name"
    }

}