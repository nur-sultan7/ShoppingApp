package com.nursultan.shoppingapp.presentation.fragments.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nursultan.shoppingapp.R


class FridgeTabFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fridge_tab, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FridgeTabFragment()
    }
}