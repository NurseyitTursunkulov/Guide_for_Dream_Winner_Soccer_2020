package com.example.guidefordreamwinnersoccer2020.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.guidefordreamwinnersoccer2020.MainViewModel
import com.example.guidefordreamwinnersoccer2020.R
import com.example.guidefordreamwinnersoccer2020.bookList.removeFullScreen
import kotlinx.android.synthetic.main.detail_viewpager.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*


class ScreenSlidePageFragment(val content: String) : Fragment() {
    val viewModel: MainViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        removeFullScreen()
        (activity as AppCompatActivity).supportActionBar?.show()
        return inflater.inflate(R.layout.detail_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content_text_view.text = content
        toolbar.title = viewModel.navigateToDetailEvent.value?.peekContent()?.title
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed();
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
}