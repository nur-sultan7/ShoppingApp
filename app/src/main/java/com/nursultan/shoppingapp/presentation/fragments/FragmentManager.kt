package com.nursultan.shoppingapp.presentation.fragments

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nursultan.shoppingapp.R

object FragmentManager {
    var currentFrag: BaseFragment? = null
    fun setFragment(activity: AppCompatActivity, fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .commit()
        currentFrag = fragment as BaseFragment
    }
}