package com.nursultan.shoppingapp.presentation.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.nursultan.shoppingapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey)
    }
}