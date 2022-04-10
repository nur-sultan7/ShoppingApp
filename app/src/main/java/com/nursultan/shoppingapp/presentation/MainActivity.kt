package com.nursultan.shoppingapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
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
    private var iAd: InterstitialAd? = null
    private var adCounter = 0
    private val adCounterMax = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        currentTheme = appPreferences.getSelectedThemeId()
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBottomNavItemsListener()
        loadInterstitialAd()
    }

    private fun loadInterstitialAd() {
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

    private fun showInterAd(adFinish: () -> Unit) {
        if (iAd != null && adCounter > adCounterMax) {
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
                        startActivity(Intent(this, SettingsActivity::class.java))
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
                    showInterAd {
                        FragmentManager.currentFrag?.onClickNew()
                    }
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