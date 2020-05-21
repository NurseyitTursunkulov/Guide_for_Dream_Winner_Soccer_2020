package com.example.guidefordreamwinnersoccer2020

import android.app.Application
import android.provider.Settings
import android.provider.Settings.Global.getString
import androidx.lifecycle.*
import com.example.guidefordreamwinnersoccer2020.bookList.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val splashState: LiveData<Event<SplashState>>
        get() = _splashState
    private val _splashState = MutableLiveData<Event<SplashState>>()

    private val _navigateToDetailEvent = MutableLiveData<Event<Book>>()
    val navigateToDetailEvent : LiveData<Event<Book>> = _navigateToDetailEvent

    private val _items = MutableLiveData<List<Book>>().apply {
        value = getBooksList()
    }

    val items: LiveData<List<Book>> = _items

    init {
        viewModelScope.launch {
            delay(500)
            _splashState.postValue(Event(SplashState.MainActivity()))
        }
    }

    fun openBook(book:Book){
        _navigateToDetailEvent.postValue(Event(book))
    }
    private fun getBooksList(): List<Book> {
        return listOf(
            Book(title = getString(R.string.book1title), body = getString(R.string.book1body),imageId = R.drawable.foot1),
            Book(title = getString(R.string.book_1_title),body = getString(R.string.book_1_body), imageId = R.drawable.foot2),
            Book(title = getString(R.string.book_2_title),body = getString(R.string.book_2_body), imageId = R.drawable.foot3),
            Book(title = getString(R.string.book_3_title),body = getString(R.string.book_3_body), imageId = R.drawable.foot4),
            Book(title = getString(R.string.book_4_title),body = getString(R.string.book_4_body), imageId = R.drawable.foot5),
            Book(title = getString(R.string.book_5_title),body = getString(R.string.book_5_body), imageId = R.drawable.foot1),
            Book(title = getString(R.string.book_6_title),body = getString(R.string.book_6_body), imageId = R.drawable.foot1),
            Book(title = getString(R.string.book_7_title),body = getString(R.string.book_7_body), imageId = R.drawable.foot1)
        )
    }
}

sealed class SplashState {
    class MainActivity : SplashState()
}
