package com.nursultan.shoppingapp.presentation.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.billing.BillingManager
import com.nursultan.shoppingapp.presentation.MainActivity
import com.nursultan.shoppingapp.presentation.fragments.BaseFragment

class SettingsFragment : PreferenceFragmentCompat(), BaseFragment {
    private var bManager: BillingManager? = null
    private var removeAdsPref: Preference? = null
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey)
        init()
    }

    private fun init() {
        removeAdsPref = findPreference(getString(R.string.remove_ads_key))
        removeAdsPref?.setOnPreferenceClickListener {
            bManager = BillingManager(activity as AppCompatActivity)
            bManager?.startConnection()
            true
        }
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        if (preference.key == getString(R.string.pref_key_theme)) {
            preference.setOnPreferenceChangeListener { _, newValue ->
                val currentThemeName = (activity as MainActivity).currentThemeName
                if (currentThemeName != newValue)
                    activity?.recreate()
                true
            }
        }
        return false
    }

    override fun onDestroy() {
        bManager?.endConnection()
        super.onDestroy()
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onClickNew() {

    }
}