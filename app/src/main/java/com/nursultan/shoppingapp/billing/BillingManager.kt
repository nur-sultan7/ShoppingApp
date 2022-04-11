package com.nursultan.shoppingapp.billing

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.*
import com.google.android.gms.common.internal.Objects

class BillingManager(private val activity: AppCompatActivity) {
    private var bilClient: BillingClient? = null

    init {
        setUpBillingClient()
    }

    private fun setUpBillingClient() {
        bilClient = BillingClient.newBuilder(activity)
            .setListener(getPurchasesUpdateListener())
            .enablePendingPurchases()
            .build()
    }

    private fun savePref(isPurchased: Boolean) {
        val pref = activity.getSharedPreferences(MAIN_PREF, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean(REMOVE_ADS_KEY, isPurchased)
        editor.apply()
    }

    fun startConnection() {
        bilClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                getItem()
            }

        })
    }

    fun endConnection() {
        bilClient?.endConnection()
    }

    private fun getItem() {
        val skuList = ArrayList<String>()
        skuList.add(REMOVE_AD)
        val skuDetailsParams = SkuDetailsParams.newBuilder()
        skuDetailsParams.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)
        bilClient?.querySkuDetailsAsync(skuDetailsParams.build()) { bResult, list ->
            if (bResult.responseCode == BillingClient.BillingResponseCode.OK) {
                if (!list.isNullOrEmpty()) {
                    val bilFlow = BillingFlowParams.newBuilder().setSkuDetails(list[0]).build()
                    bilClient?.launchBillingFlow(activity, bilFlow)
                }
            }
        }
    }

    private fun getPurchasesUpdateListener() =
        PurchasesUpdatedListener { bResult, list ->
            if (bResult.responseCode == BillingClient.BillingResponseCode.OK) {
                list?.get(0)?.let {
                    nonConsumableItem(it)
                }
            }
        }

    private fun nonConsumableItem(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged) {
                val acParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken).build()
                bilClient?.acknowledgePurchase(acParams) {
                    if (it.responseCode == BillingClient.BillingResponseCode.OK) {
                        savePref(true)
                        Toast.makeText(activity, "Remove ads is success!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        savePref(false)
                        Toast.makeText(activity, "Remove ads is not success!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    companion object {
        const val REMOVE_AD = "remove_ad"
        const val MAIN_PREF = "main_pref"
        const val REMOVE_ADS_KEY = "remove_ads_key"
    }
}