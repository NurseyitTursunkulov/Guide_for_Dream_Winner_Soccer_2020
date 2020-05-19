package com.example.guidefordreamwinnersoccer2020.bookList

import android.view.WindowManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager


fun MainFragment.removeFullScreen() {
    requireActivity().window.apply {
        addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}

 fun MainFragment.initAdapter() {
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

@BindingAdapter("imageResource")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}