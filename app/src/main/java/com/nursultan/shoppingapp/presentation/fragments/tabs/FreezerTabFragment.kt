package com.nursultan.shoppingapp.presentation.fragments.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nursultan.shoppingapp.R


class FreezerTabFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_freezer_tab, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FreezerTabFragment()
    }
}