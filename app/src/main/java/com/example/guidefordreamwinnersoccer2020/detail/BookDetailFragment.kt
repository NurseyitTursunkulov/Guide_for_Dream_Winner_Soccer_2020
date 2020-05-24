package com.example.guidefordreamwinnersoccer2020.detail

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.guidefordreamwinnersoccer2020.MainViewModel
import com.example.guidefordreamwinnersoccer2020.R
import com.example.guidefordreamwinnersoccer2020.bookList.removeFullScreen
import kotlinx.android.synthetic.main.activity_screen_slide.*
import kotlinx.android.synthetic.main.fragment_book_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class BookDetailFragment : Fragment() {
    val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        removeFullScreen()
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.show()
        return inflater.inflate(R.layout.activity_screen_slide, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = viewModel.navigateToDetailEvent.value?.peekContent()
        pager.adapter = ScreenSlidePagerAdapter(requireActivity())

        pageIndicatorView.count =
            book?.listOfContentPerPage?.size ?: 1 // specify total count of indicators

        pageIndicatorView.selection = 1

        pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    pageIndicatorView.selection = position
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
            if (pager.currentItem == 0) {
                // If the user is currently looking at the first step, allow the system to handle the
                // Back button. This calls finish() on this activity and pops the back stack.
                this@BookDetailFragment.findNavController().navigateUp()
            } else {
                // Otherwise, select the previous step.
                pager.currentItem = pager.currentItem - 1
            }
        }

    }


    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int =
            viewModel.navigateToDetailEvent.value?.peekContent()?.listOfContentPerPage?.size ?: 1

        override fun createFragment(position: Int): Fragment =
            ScreenSlidePageFragment(
                viewModel.navigateToDetailEvent.value?.peekContent()?.listOfContentPerPage?.get(
                    position
                )?:""
            )
    }
}