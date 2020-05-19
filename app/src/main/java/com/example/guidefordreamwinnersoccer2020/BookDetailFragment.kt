package com.example.guidefordreamwinnersoccer2020

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.guidefordreamwinnersoccer2020.databinding.FragmentBookDetailBinding
import kotlinx.android.synthetic.main.fragment_book_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class BookDetailFragment : Fragment() {

    lateinit var viewDataBinding: FragmentBookDetailBinding
    val viewModel: MainViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        viewDataBinding = FragmentBookDetailBinding.inflate(inflater, container, false).apply {
            bookInfo = viewModel.navigateToDetailEvent.value?.peekContent()
        }
        return viewDataBinding.root
    }

}