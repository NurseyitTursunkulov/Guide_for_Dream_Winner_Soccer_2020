package com.example.guidefordreamwinnersoccer2020

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val splashState: LiveData<Event<SplashState>>
        get() = _splashState
    private val _splashState = MutableLiveData<Event<SplashState>>()

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