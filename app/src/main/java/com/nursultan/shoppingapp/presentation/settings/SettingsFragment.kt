package com.nursultan.shoppingapp.presentation.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.nursultan.shoppingapp.R

class SettingsFragment : PreferenceFragmentCompat() {

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
        }
        return false
    }
}