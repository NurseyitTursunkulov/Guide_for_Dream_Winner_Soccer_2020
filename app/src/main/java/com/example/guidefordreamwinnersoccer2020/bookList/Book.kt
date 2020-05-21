package com.example.guidefordreamwinnersoccer2020.bookList

import android.icu.text.CaseMap
import java.util.*

data class Book(
    val title: String = "dfrfa",
    val id: String = UUID.randomUUID().toString(),
    var imageId: Int,
    var body: String = ""
)
