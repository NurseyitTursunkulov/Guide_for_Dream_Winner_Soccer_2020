package com.example.guidefordreamwinnersoccer2020.detail

import kotlinx.android.synthetic.main.activity_screen_slide.*


fun BookDetailFragment.initPendingIndicatorView() {
    val book = viewModel.navigateToDetailEvent.value?.peekContent()

    pageIndicatorView.count =
        book?.listOfContentPerPage?.size ?: 1 // specify total count of indicators

    pageIndicatorView.selection = 1
}