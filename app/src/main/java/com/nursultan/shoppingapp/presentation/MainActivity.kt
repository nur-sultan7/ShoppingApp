package com.nursultan.shoppingapp.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.billing.BillingManager
import com.nursultan.shoppingapp.databinding.ActivityMainBinding
import com.nursultan.shoppingapp.presentation.fragments.FragmentManager
import com.nursultan.shoppingapp.presentation.fragments.FridgeFragment
import com.nursultan.shoppingapp.presentation.fragments.NoteFragment
import com.nursultan.shoppingapp.presentation.fragments.ShopListNamesFragment
import com.nursultan.shoppingapp.presentation.settings.SettingsFragment
import com.nursultan.shoppingapp.utils.AppPreferences

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val appPreferences by lazy {
        AppPreferences(this)
    }
    val currentThemeName: String?
        get() = appPreferences.getSelectedTheme()
    private var currentTheme: Int = 0
    private var iAd: InterstitialAd? = null
    private var adCounter = 0
    private val adCounterMax = 3
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentTheme = appPreferences.getSelectedThemeId()
        setTheme(currentTheme)
        pref = getSharedPreferences(BillingManager.MAIN_PREF, MODE_PRIVATE)
        setContentView(binding.root)
        setBottomNavItemsListener()
        var startItemId = R.id.nav_shopping_list
        savedInstanceState?.let {
            startItemId = it.getInt(START_ITEM_ID)
        }
        binding.bNav.selectedItemId = startItemId
        loadInterstitialAd()
    }

    private fun loadInterstitialAd() {
        if (removeAdsIsPurchased())
            return
        val request = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            getString(R.string.inter_ad_id),
            request,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    iAd = null
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    iAd = ad
                }
            })
    }

    private fun removeAdsIsPurchased() =
        pref.getBoolean(BillingManager.REMOVE_ADS_KEY, false)

    private fun showInterAd(adFinish: () -> Unit) {
        if (iAd != null && !removeAdsIsPurchased() && adCounter > adCounterMax) {
            iAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    iAd = null
                    loadInterstitialAd()
                    adFinish.invoke()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    iAd = null
                    loadInterstitialAd()
                }

                override fun onAdShowedFullScreenContent() {
                    iAd = null
                    loadInterstitialAd()
                }
            }
            iAd?.show(this)
            adCounter = 0
        } else {
            adCounter++
            adFinish.invoke()
        }
    }

    private fun setBottomNavItemsListener() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_setting -> {
                    showInterAd {
                        FragmentManager.setFragment(this, SettingsFragment.newInstance())
                    }
                }
                R.id.nav_notes -> {
                    showInterAd {
                        FragmentManager.setFragment(this, NoteFragment.newInstance())
                    }
                }
                R.id.nav_shopping_list -> {
                    FragmentManager.setFragment(this, ShopListNamesFragment.newInstance())
                }
                R.id.nav_new_item -> {
                    FragmentManager.setFragment(this, FridgeFragment.newInstance())
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        if (currentTheme != appPreferences.getSelectedThemeId()) recreate()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.apply {
            putInt(START_ITEM_ID, binding.bNav.selectedItemId)
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }

    companion object {
        const val START_ITEM_ID = "start item id"
    }
}