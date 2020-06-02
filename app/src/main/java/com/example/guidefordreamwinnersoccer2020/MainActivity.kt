package com.example.guidefordreamwinnersoccer2020

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.androidsx.rateme.RateMeDialog
import com.androidsx.rateme.RateMeDialogTimer
import com.example.guidefordreamwinnersoccer2020.util.initAdds
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModel()
    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.content_main)

        initAdds(this)
        mInterstitialAd = InterstitialAd(this).apply {
            adUnitId = "ca-app-pub-3940256099942544/1033173712"
            loadAd(AdRequest.Builder().build())
            adListener = object : AdListener() {
                override fun onAdClosed() {
                    Log.d("Nurs", "onAdClosed")
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                }
            }
        }

        viewModel.interstitialAd = mInterstitialAd

        RateMeDialogTimer.onStart(this)
        if (RateMeDialogTimer.shouldShowRateDialog(this, 1, 2)) {
            RateMeDialog.Builder(packageName, "")
                .setHeaderBackgroundColor(resources.getColor(R.color.colorPrimary))
                .setBodyBackgroundColor(resources.getColor(R.color.dialog_body))
                .showAppIcon(R.mipmap.ic_launcher)
                .enableFeedbackByEmail("")
                .setRateButtonBackgroundColor(resources.getColor(R.color.dialog_button))
                .build()
                .show(fragmentManager, "plain-dialog")
        }

    }
}