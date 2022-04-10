package com.nursultan.shoppingapp.presentation.settings

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.billing.BillingManager

class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var bManager: BillingManager
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        if (preference.key == getString(R.string.pref_key_theme)) {
            preference.setOnPreferenceChangeListener { _, newValue ->
                val currentThemeName = (activity as SettingsActivity).currentTheme
                if (currentThemeName != newValue)
                    activity?.recreate()
                true
            }
        } else if (preference.key == getString(R.string.remove_ads_key)) {
            preference.setOnPreferenceChangeListener { _, _ ->
                Log.d("Billing test", "remove ads pressed")
                bManager = BillingManager(activity as AppCompatActivity)
                bManager.startConnection()
                true
            }
        }
        return false
    }

    override fun onDestroy() {
        bManager.endConnection()
        super.onDestroy()
    }
}