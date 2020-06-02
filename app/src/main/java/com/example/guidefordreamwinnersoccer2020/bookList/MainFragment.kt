package com.example.guidefordreamwinnersoccer2020.bookList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guidefordreamwinnersoccer2020.MainViewModel
import com.example.guidefordreamwinnersoccer2020.R
import com.example.guidefordreamwinnersoccer2020.databinding.FragmentMainBinding
import com.example.guidefordreamwinnersoccer2020.util.EventObserver
import com.example.guidefordreamwinnersoccer2020.util.removeFullScreen
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

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
        viewModel.navigateToDetailEvent.observe(viewLifecycleOwner,
            EventObserver {
                findNavController().navigate(R.id.action_mainFragment_to_bookDetailFragment)
            })

        initAdapter()
    }

    override fun onPause() {
        viewModel.adView?.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModel.adView?.resume()
    }

    override fun onDestroy() {
        viewModel.adView?.destroy()
        super.onDestroy()
    }
}