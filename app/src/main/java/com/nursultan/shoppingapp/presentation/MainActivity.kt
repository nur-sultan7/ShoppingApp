package com.nursultan.shoppingapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.databinding.ActivityMainBinding
import com.nursultan.shoppingapp.presentation.fragments.FragmentManager
import com.nursultan.shoppingapp.presentation.fragments.NoteFragment
import com.nursultan.shoppingapp.presentation.fragments.ShopListNamesFragment
import com.nursultan.shoppingapp.presentation.settings.SettingsActivity
import com.nursultan.shoppingapp.utils.AppPreferences

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val appPreferences by lazy {
        AppPreferences(this)
    }
    private var currentTheme: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        currentTheme = appPreferences.getSelectedThemeId()
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBottomNavItemsListener()
    }

    private fun setBottomNavItemsListener() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_setting -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
                R.id.nav_notes -> {
                    FragmentManager.setFragment(this, NoteFragment.newInstance())
                }
                R.id.nav_shopping_list -> {
                    FragmentManager.setFragment(this, ShopListNamesFragment.newInstance())
                }
                R.id.nav_new_item -> {
                    FragmentManager.currentFrag?.onClickNew()
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        if (currentTheme != appPreferences.getSelectedThemeId()) recreate()
    }


}