package com.nursultan.shoppingapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.ShoppingApp
import com.nursultan.shoppingapp.data.database.model.ShoppingListNameDbModel
import com.nursultan.shoppingapp.databinding.FragmentShopListNamesBinding
import com.nursultan.shoppingapp.presentation.MainViewModel
import com.nursultan.shoppingapp.presentation.ViewModelFactory
import com.nursultan.shoppingapp.presentation.adapters.ShopListNamesAdapter
import com.nursultan.shoppingapp.presentation.dialogs.NewListDialog
import com.nursultan.shoppingapp.utils.TimeManager
import java.lang.RuntimeException


class ShopListNamesFragment : BaseFragment() {
    private var _binding: FragmentShopListNamesBinding? = null
    private val binding: FragmentShopListNamesBinding
        get() = _binding ?: throw RuntimeException("binding is null")

    private val viewModel: MainViewModel by activityViewModels {
        ViewModelFactory((requireContext().applicationContext as ShoppingApp).appDatabase)
    }
    private lateinit var adapter: ShopListNamesAdapter

    override fun onClickNew() {
        NewListDialog.showDialog(requireContext()) { listName ->
            viewModel.insertShoppingList(
                ShoppingListNameDbModel(
                    name = listName,
                    time = TimeManager.getCurrentTime(),
                    itemsId = "",
                    allItemsCounter = 0,
                    checkedItemsCounter = 0
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopListNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setObservers()
    }

    private fun initViews() {
        binding.rcViewShopListNames.layoutManager = LinearLayoutManager(activity)
        adapter = ShopListNamesAdapter()
        binding.rcViewShopListNames.adapter = adapter
    }

    private fun setObservers() {
        viewModel.allShoppingListNames.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ShopListNamesFragment()
    }
}