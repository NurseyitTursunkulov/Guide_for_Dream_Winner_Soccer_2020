package com.example.guidefordreamwinnersoccer2020

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guidefordreamwinnersoccer2020.util.EventObserver
import com.example.guidefordreamwinnersoccer2020.util.divideTextToParts
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = viewModel.items.value
        content?.let { bookList ->
            divideTextToParts(bookList)
        }

        viewModel.splashState.observe(viewLifecycleOwner,
            EventObserver {
                when (it) {
                    is SplashState.MainActivity -> {
                        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                    }
                }
            })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

}