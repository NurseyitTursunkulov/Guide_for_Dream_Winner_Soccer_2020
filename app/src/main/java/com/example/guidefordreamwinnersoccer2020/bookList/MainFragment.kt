package com.example.guidefordreamwinnersoccer2020.bookList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guidefordreamwinnersoccer2020.EventObserver
import com.example.guidefordreamwinnersoccer2020.MainViewModel
import com.example.guidefordreamwinnersoccer2020.R
import com.example.guidefordreamwinnersoccer2020.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    lateinit var viewDataBinding: FragmentMainBinding
    lateinit var listAdapter: TasksAdapter
    val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        removeFullScreen()
        (activity as AppCompatActivity).supportActionBar?.show()
        viewDataBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.navigateToDetailEvent.observe(viewLifecycleOwner,EventObserver{
            findNavController().navigate(R.id.action_mainFragment_to_bookDetailFragment)
        })
    }
}