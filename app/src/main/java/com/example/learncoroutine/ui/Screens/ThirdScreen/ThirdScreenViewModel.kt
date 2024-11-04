package com.example.learncoroutine.ui.Screens.ThirdScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThirdScreenViewModel @Inject constructor() : ViewModel() {

    var a = mutableStateOf("Hi")
    var b = mutableStateOf("Hello")

    init {
        viewModelScope.launch {
            a.value = executeFirstFunction()
            b.value = executeSecondFunction()
        }

        viewModelScope.launch {
            executeThirdFunction()
        }

    }

    private suspend fun executeFirstFunction(): String = withContext(Dispatchers.IO) {
        delay(2000)
        Log.d("ThirdScreenViewModel", "Completed First function ${Thread.currentThread().name}")

        "This is First Function."
    }

    private suspend fun executeSecondFunction(): String = withContext(Dispatchers.IO) {
        delay(2000)
        Log.d("ThirdScreenViewModel", "Completed Second function: ${Thread.currentThread().name}")

        "This is Second Function."
    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun executeThirdFunction() {
        GlobalScope.launch {
            Log.d("ThirdScreenViewModel", "Third function: ${Thread.currentThread().name}")
        }
    }


}