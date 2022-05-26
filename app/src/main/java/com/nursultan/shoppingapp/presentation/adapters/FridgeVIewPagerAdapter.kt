package com.nursultan.shoppingapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nursultan.shoppingapp.presentation.fragments.tabs.FreezerTabFragment
import com.nursultan.shoppingapp.presentation.fragments.tabs.FridgeTabFragment

class FridgeVIewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return TABS_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FRIDGE_TAB -> FridgeTabFragment()
            FREEZER_TAB -> FreezerTabFragment()
            else -> throw RuntimeException("This fragment is not exist!")
        }
    }

    companion object {
        const val TABS_COUNT = 2
        const val FRIDGE_TAB = 0
        const val FREEZER_TAB = 1
    }
}