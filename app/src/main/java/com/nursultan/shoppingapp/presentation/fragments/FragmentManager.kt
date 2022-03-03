package com.nursultan.shoppingapp.presentation.fragments

import androidx.appcompat.app.AppCompatActivity
import com.nursultan.shoppingapp.R

object FragmentManager {
    var currentFrag: BaseFragment? = null
    fun setFragment(activity: AppCompatActivity, fragment: BaseFragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .commit()
        currentFrag = fragment
    }
}