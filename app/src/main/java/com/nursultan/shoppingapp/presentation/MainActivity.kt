package com.nursultan.shoppingapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBottomNavItemsListener()
    }

    private fun setBottomNavItemsListener() {
        binding.bnav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_setting -> {
                    Log.d("Nav click", "nav_setting")
                }
                R.id.nav_notes -> {
                    Log.d("Nav click", "nav_notes")
                }
                R.id.nav_shopping_list -> {
                    Log.d("Nav click", "nav_shopping_list")
                }
                R.id.nav_new_item -> {
                    Log.d("Nav click", "nav_new_item")
                }
            }
            true
        }
    }
}