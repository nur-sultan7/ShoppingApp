package com.nursultan.shoppingapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.ShoppingApp
import com.nursultan.shoppingapp.databinding.FragmentShopListNamesBinding
import com.nursultan.shoppingapp.presentation.MainViewModel
import com.nursultan.shoppingapp.presentation.ViewModelFactory
import com.nursultan.shoppingapp.presentation.dialogs.NewListDialog
import java.lang.RuntimeException


class ShopListNamesFragment : BaseFragment() {
    private var _binding: FragmentShopListNamesBinding? = null
    private val binding: FragmentShopListNamesBinding
        get() = _binding ?: throw RuntimeException("binding is null")

    private val viewModel: MainViewModel by activityViewModels {
        ViewModelFactory((requireContext().applicationContext as ShoppingApp).appDatabase)
    }

    override fun onClickNew() {
        NewListDialog.showDialog(requireContext()) { name ->
            Log.d("App Test", "name is: $name")
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

    companion object {
        @JvmStatic
        fun newInstance() = ShopListNamesFragment()
    }
}