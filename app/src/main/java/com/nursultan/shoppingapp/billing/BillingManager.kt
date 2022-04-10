package com.nursultan.shoppingapp.billing

import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.*

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

    fun startConnection() {
        bilClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                getItem()
            }

        })
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

                    }
                }
            }
        }
    }

    companion object {
        const val REMOVE_AD = "remove_ad"
    }
}