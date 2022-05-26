package com.nursultan.shoppingapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.databinding.FragmentFridgeBinding
import com.nursultan.shoppingapp.databinding.FragmentNoteBinding


class FridgeFragment : Fragment() {
    private var _biding: FragmentFridgeBinding? = null
    private val biding: FragmentFridgeBinding
        get() = _biding ?: throw RuntimeException("FragmentFridgeBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _biding = FragmentFridgeBinding.inflate(inflater, container, false)
        return biding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TabLayoutMediator(biding.tabLayoutFridge, biding.viewPagerFridge){tab, position->

        }.attach()
    }


    override fun onDestroyView() {
        _biding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FridgeFragment()
    }
}