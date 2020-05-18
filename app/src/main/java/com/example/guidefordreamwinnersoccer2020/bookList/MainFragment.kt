package com.example.guidefordreamwinnersoccer2020.bookList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guidefordreamwinnersoccer2020.MainViewModel
import com.example.guidefordreamwinnersoccer2020.R
import com.example.guidefordreamwinnersoccer2020.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentMainBinding
    private lateinit var listAdapter: TasksAdapter
    val viewModel : MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        (activity as AppCompatActivity).supportActionBar?.show()
        viewDataBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

    }

    private fun initAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = TasksAdapter(viewModel)
            viewDataBinding.recyclerViewBooks.adapter = listAdapter
            viewDataBinding.recyclerViewBooks.layoutManager = GridLayoutManager(requireContext(), 2)
            viewModel.items.observe(viewLifecycleOwner, Observer {
                listAdapter.submitList(it)
            })
        } else {
    //            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }

}