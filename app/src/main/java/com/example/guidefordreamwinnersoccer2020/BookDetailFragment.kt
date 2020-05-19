package com.example.guidefordreamwinnersoccer2020

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.guidefordreamwinnersoccer2020.bookList.Book
import com.example.guidefordreamwinnersoccer2020.databinding.ActivityBookInformationBinding
import com.example.guidefordreamwinnersoccer2020.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookDetailFragment : Fragment() {

    lateinit var viewDataBinding: ActivityBookInformationBinding
    val viewModel:MainViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        viewDataBinding = ActivityBookInformationBinding.inflate(inflater, container, false).apply {
            bookInfo = viewModel.navigateToDetailEvent.value?.peekContent()
        }
        return viewDataBinding.root
    }

}