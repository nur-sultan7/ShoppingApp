package com.nursultan.shoppingapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.ShoppingApp
import com.nursultan.shoppingapp.data.database.model.ShopListItemDbModel
import com.nursultan.shoppingapp.data.database.model.ShopListNameItemDbModel
import com.nursultan.shoppingapp.databinding.ActivityShopListBinding
import com.nursultan.shoppingapp.presentation.adapters.ShopListItemAdapter
import com.nursultan.shoppingapp.presentation.dialogs.EditListItemDialog
import com.nursultan.shoppingapp.utils.ShareHelper
import com.nursultan.shoppingapp.utils.VisibilitySetter

class ShopListActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityShopListBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory((application as ShoppingApp).appDatabase)
    }
    private val defPref by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }
    private lateinit var shopListNameItem: ShopListNameItemDbModel
    private lateinit var edItemName: EditText
    private lateinit var adapter: ShopListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getSelectedTheme())
        setContentView(binding.root)
        init()
        initViews()
        setShopListItemsObservers()
        setOnClickListeners()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shop_list_menu, menu)
        menu?.let {
            val saveItem = menu.findItem(R.id.save_item)
            saveItem.isVisible = false
            val addItem = menu.findItem(R.id.add_item)
            edItemName = addItem.actionView.findViewById(R.id.edShopListItem)
            addItem.setOnActionExpandListener(expandActionView(saveItem))

        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun expandActionView(saveItem: MenuItem) =
        object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                saveItem.isVisible = true
                edItemName.doOnTextChanged { text, _, _, _ ->
                    onTextChanged(text)
                }
                setLibraryObserver()
                viewModel.getLibraryItems("%%")
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                saveItem.isVisible = false
                edItemName.addTextChangedListener()
                edItemName.text = null
                invalidateOptionsMenu()
                removeLibraryObserver()
                setShopListItemsObservers()
                return true
            }

        }

    private fun onTextChanged(s: CharSequence?) {
        viewModel.getLibraryItems("%$s%")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_item -> {
                setOnMenuSaveItemListener()
            }
            R.id.delete_list -> {
                viewModel.deleteShoppingList(shopListNameItem.id)
                finish()
            }
            R.id.clear_list -> {
                viewModel.deleteShopListItems(shopListNameItem.id)
            }
            R.id.share_list -> {
                startActivity(
                    Intent.createChooser(
                        ShareHelper.shareShopList(
                            adapter.currentList,
                            shopListNameItem.name
                        ), getString(R.string.share_list_chooser_title)
                    )
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setOnMenuSaveItemListener() {
        if (edItemName.text.toString().isNotEmpty()) {
            viewModel.insertShopListItem(createShopListItem(edItemName.text.toString()))
            edItemName.text = null
        }
    }

    private fun createShopListItem(name: String) = ShopListItemDbModel(
        name = name,
        listId = shopListNameItem.id
    )

    private fun init() {
        shopListNameItem = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItemDbModel
    }

    private fun initViews() = with(binding) {
        rcViewShopListItems.layoutManager = LinearLayoutManager(this@ShopListActivity)
        adapter = ShopListItemAdapter()
        rcViewShopListItems.adapter = adapter
    }

    private fun setShopListItemsObservers() {
        viewModel.getAllShoppingListItems(shopListNameItem.id).observe(this) { list ->
            adapter.submitList(list)
            binding.tvEmptyList.visibility = VisibilitySetter.setVisibilityByList(list)
        }
    }

    private fun setLibraryObserver() {
        viewModel.libraryItems.observe(this)
        {
            val libraryList = mutableListOf<ShopListItemDbModel>()
            for (item in it) {
                libraryList.add(
                    ShopListItemDbModel(
                        id = item.id,
                        name = item.name,
                        type = ShopListItemAdapter.LIBRARY_ITEM,
                        listId = 0
                    )
                )
            }
            adapter.submitList(libraryList)
            binding.tvEmptyList.visibility = VisibilitySetter.setVisibilityByList(libraryList)
        }
    }

    private fun removeLibraryObserver() {
        viewModel.libraryItems.removeObservers(this)
    }

    private fun setOnClickListeners() {
        adapter.onCheckClickListener = {
            viewModel.updateShopListItem(it)
        }
        adapter.onEditClickListener = {
            EditListItemDialog.showDialog(this, it) { item ->
                viewModel.updateShopListItem(item)
            }
        }
        adapter.onLibraryEditClickListener = {
            EditListItemDialog.showDialog(this, it) { item ->
                viewModel.updateLibraryItem(item)
            }
        }
        adapter.onLibraryDeleteClickListener = {
            viewModel.deleteLibraryItem(it)
        }
        adapter.onLibraryItemClickListener = {
            viewModel.insertShopListItem(createShopListItem(it))
        }
    }

    private fun saveItemsCheckedState() {
        var checkedCounter = 0
        adapter.currentList.forEach { if (it.checked) checkedCounter++ }
        val allItemsCounter = adapter.itemCount
        viewModel.updateShoppingListName(
            shopListNameItem.copy(
                allItemsCounter = allItemsCounter,
                checkedItemsCounter = checkedCounter
            )
        )
    }

    override fun onBackPressed() {
        saveItemsCheckedState()
        super.onBackPressed()
    }

    companion object {
        const val SHOP_LIST_NAME = "list_name"
    }

    private fun getSelectedTheme(): Int {
        return if (defPref.getString("theme_style_preference", null) == "Green")
            R.style.Theme_ShoppingAppGreen
        else
            R.style.Theme_ShoppingAppBlack
    }
}