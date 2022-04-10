package com.nursultan.shoppingapp.billing

import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.*

class BillingManager(private val activity: AppCompatActivity) {
    private var bilClient: BillingClient? = null
    private fun setUpBillingClient() {
        bilClient = BillingClient.newBuilder(activity)
            .setListener(getPurchasesUpdateListener())
            .enablePendingPurchases()
            .build()
    }

    private fun getPurchasesUpdateListener() =
        PurchasesUpdatedListener { bResult, list ->
            if (bResult.responseCode == BillingClient.BillingResponseCode.OK) {

            }
        }

    private fun nonConsumableItem(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged) {
                val acParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken).build()
                bilClient?.acknowledgePurchase(acParams) {
                    if (it.responseCode == BillingClient.BillingResponseCode.OK) {

                    }
                }
            }
        }
    }
}