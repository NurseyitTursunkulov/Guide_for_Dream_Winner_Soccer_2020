package com.example.guidefordreamwinnersoccer2020

import android.app.Application

fun MainViewModel.getString(int: Int): String {
    return getApplication<Application>().resources.getString(int)
}