package com.nursultan.shoppingapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.databinding.FragmentFridgeBinding
import com.nursultan.shoppingapp.presentation.adapters.FridgeVIewPagerAdapter
import com.nursultan.shoppingapp.presentation.fragments.tabs.FreezerTabFragment
import com.nursultan.shoppingapp.presentation.fragments.tabs.FridgeTabFragment


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
        val fragments = arrayListOf<Fragment>()
        fragments.add(FridgeTabFragment())
        fragments.add(FreezerTabFragment())
        biding.viewPagerFridge.adapter = FridgeVIewPagerAdapter(this, fragments)
        TabLayoutMediator(biding.tabLayoutFridge, biding.viewPagerFridge) { tab, position ->
            when (position) {
                FRIDGE_TAB -> tab.text = getString(R.string.label_fridge)
                FREEZER_TAB -> tab.text = getString(R.string.label_freezer)
            }
        }

        biding.tabLayoutFridge.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                biding.viewPagerFridge.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        biding.viewPagerFridge.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                biding.tabLayoutFridge.selectTab(biding.tabLayoutFridge.getTabAt(position))
            }
        }
        )
    }
    override fun onDestroyView() {
        _biding = null
        super.onDestroyView()
    }

    companion object {
        const val TABS_COUNT = 2
        const val FRIDGE_TAB = 0
        const val FREEZER_TAB = 1

        @JvmStatic
        fun newInstance() = FridgeFragment()
    }
}