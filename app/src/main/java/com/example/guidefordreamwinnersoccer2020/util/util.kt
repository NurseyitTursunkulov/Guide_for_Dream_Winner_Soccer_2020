package com.example.guidefordreamwinnersoccer2020.util

import android.app.Application
import android.view.ViewTreeObserver
import com.example.guidefordreamwinnersoccer2020.MainViewModel
import com.example.guidefordreamwinnersoccer2020.SplashFragment
import com.example.guidefordreamwinnersoccer2020.bookList.Book
import kotlinx.android.synthetic.main.splash_fragment.*

fun MainViewModel.getString(int: Int): String {
    return getApplication<Application>().resources.getString(int)
}

fun SplashFragment.divideTextToParts(bookList: List<Book>) {
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
            bookList.forEach { book ->
                book.listOfContentPerPage = book.body.chunked(content.length)
            }
            return true
        }
    }
    )
}

//fun chunk(text: String, del: Int): List<String> {
//    val splitedList = mutableListOf<String>()
//    val charArray = text.toCharArray()
//    val endFor = charArray.size / del
//    var start = 0
//    var endIndex = del
//    var shifter = 0
//    for (x in 0..endFor) {
//        start = del * x + shifter
//        endIndex = start + del
//        val newCharArray = charArray.copyOfRange(start, endIndex).toMutableList()
//        while (!newCharArray[newCharArray.size - 1].equals(" ")) {
//            newCharArray.add(charArray[endIndex + 1])
//            shifter += 1
//        }
//        splitedList.add(newCharArray.toString())
//
//    }
//    return splitedList
//}