package com.nursultan.shoppingapp.presentation.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.utils.AppPreferences

class SettingsActivity : AppCompatActivity() {
    private val appPreferences by lazy {
        AppPreferences(this)
    }
    private var _currentTheme = 0
    val currentTheme: String?
        get() = appPreferences.getSelectedTheme()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _currentTheme = appPreferences.getSelectedThemeId()
        setTheme(_currentTheme)
        setContentView(R.layout.activity_settings)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.settingsPlaceHolder, SettingsFragment())
                .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        if (_currentTheme != appPreferences.getSelectedThemeId()) recreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}