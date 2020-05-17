package com.example.guidefordreamwinnersoccer2020

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guidefordreamwinnersoccer2020.bookList.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val splashState: LiveData<Event<SplashState>>
        get() = _splashState
    private val _splashState = MutableLiveData<Event<SplashState>>()

    private val _items = MutableLiveData<List<Book>>().apply {
        value = listOf(
            Book(imageId = R.drawable.foot1),
            Book(imageId = R.drawable.foot2),
            Book(imageId = R.drawable.foot3),
            Book(imageId = R.drawable.foot4),
            Book(imageId = R.drawable.foot5),
            Book(imageId = R.drawable.foot1)
        )
    }
    val items: LiveData<List<Book>> = _items

    init {
        viewModelScope.launch {
            delay(500)
            _splashState.postValue(Event(SplashState.MainActivity()))
        }
    }
}

sealed class SplashState {
    class MainActivity : SplashState()
}