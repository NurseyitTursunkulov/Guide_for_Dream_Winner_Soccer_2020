package com.example.guidefordreamwinnersoccer2020.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.guidefordreamwinnersoccer2020.MainViewModel
import com.example.guidefordreamwinnersoccer2020.R
import com.example.guidefordreamwinnersoccer2020.bookList.getAdRequest
import com.example.guidefordreamwinnersoccer2020.util.EventObserver
import com.example.guidefordreamwinnersoccer2020.util.removeFullScreen
import kotlinx.android.synthetic.main.detail_viewpager.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*


class ScreenSlidePageFragment(private val content: String, private val position: Int) :
    Fragment(R.layout.detail_viewpager) {

    val viewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeFullScreen()
        (activity as AppCompatActivity).supportActionBar?.show()
        content_text_view.text = content
        toolbar.title = viewModel.navigateToDetailEvent.value?.peekContent()?.title
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        viewModel.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            if (viewModel.interstitialAd.isLoaded) {
                viewModel.interstitialAd.show()
            } else {
                Log.d("Nurs", "slidepage The interstitial wasn't loaded yet.")
            }
        })
        if (viewModel.showAdvertState) {
            ad_view_detail_pager.visibility = VISIBLE
            val adRequest = getAdRequest()
            ad_view_detail_pager.loadAd(adRequest)
        }
        val images =
            intArrayOf(
                R.drawable.foot5 //ok
                , R.drawable.image1// ok
                , R.drawable.image2, //ok
                R.drawable.foot12, //ok
                R.drawable.foot13,// ok
                R.drawable.foot15,// ok
                R.drawable.foot16,// ok
                R.drawable.foot18,
                R.drawable.foot20,
                R.drawable.foot21,
                R.drawable.foot19
            )
        val rand = Random()
        imageView.load(images[rand.nextInt(images.size)])
    }

    override fun onResume() {
        super.onResume()
        Log.d("Nurs", "onResume pos $position")
        if (position % 2 == 0) {
            Log.d("Nurs", "pos if $position")
            viewModel.showAdvert()
        }
    }
}