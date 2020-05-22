package com.example.guidefordreamwinnersoccer2020.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.guidefordreamwinnersoccer2020.R
import com.example.guidefordreamwinnersoccer2020.bookList.removeFullScreen
import kotlinx.android.synthetic.main.fragment_book_detail.*

class ScreenSlidePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        removeFullScreen()
        (activity as AppCompatActivity).supportActionBar?.show()
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        (activity as AppCompatActivity).supportActionBar?.show()
        return inflater.inflate(R.layout.detail_viewpager, container, false)
    }

    override fun onResume() {
        super.onResume()
    }
}