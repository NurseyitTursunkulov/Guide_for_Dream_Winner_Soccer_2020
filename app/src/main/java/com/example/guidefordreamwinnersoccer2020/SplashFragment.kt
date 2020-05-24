package com.example.guidefordreamwinnersoccer2020

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import kotlinx.android.synthetic.main.splash_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SplashFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = viewModel.items.value
        content?.let {bookList->
            content_tv_for_count.viewTreeObserver.addOnPreDrawListener(object :
                ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    content_tv_for_count.viewTreeObserver.removeOnPreDrawListener(this)

                    val maxLinesVisible =
                        content_tv_for_count.height / content_tv_for_count.lineHeight

                    content_tv_for_count.maxLines = maxLinesVisible
                    val start = content_tv_for_count.layout.getLineStart(0)
                    val end = content_tv_for_count.layout.getLineEnd(maxLinesVisible - 1)

                    val content = content_tv_for_count.text.toString().substring(start, end)
                    bookList.forEach {book->
                       book.listOfContentPerPage = book.body.chunked(content.length)
                    }
                    return true
                }
            }
            )

        }
//        requireActivity().actionBar?.hide()
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
        viewModel.splashState.observe(viewLifecycleOwner, EventObserver {
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
fun chunk(text:String, del:Int):List<String>{
    val splitedList = mutableListOf<String>()
    val charArray = text.toCharArray()
    val endFor = charArray.size/del
    var start= 0
    var endIndex = del
    var shifter = 0
    for (x in 0..endFor){
        start = del*x + shifter
        endIndex = start + del
        val newCharArray = charArray.copyOfRange(start, endIndex).toMutableList()
        while (!newCharArray[newCharArray.size-1].equals(" ")){
            newCharArray.add(charArray[endIndex+1])
            shifter+=1
        }
        splitedList.add(newCharArray.toString())

    }
    return splitedList
}