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
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThirdScreenViewModel @Inject constructor() : ViewModel() {
    var a = mutableStateOf("Hi")
    var b = mutableStateOf("Hello")

    init {
        viewModelScope.launch {
            a.value = executeFirstFunction()
            b.value = executeSecondFunction()
            runBlocking {
                Log.d("ThirdScreenViewModel", "Test function ${Thread.currentThread().name}")
            }
        }

        viewModelScope.launch {
            executeThirdFunction()
        }

        viewModelScope.launch {
            executeFourthFunction()
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
        GlobalScope.launch(Dispatchers.Unconfined) {
            Log.d("ThirdScreenViewModel", "Third function: ${Thread.currentThread().name}")
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun executeFourthFunction() {
        GlobalScope.launch(newSingleThreadContext("Testing Thread")) {
            Log.d(
                "ThirdScreenViewModel",
                "Fourth function made own thread: ${Thread.currentThread().name}"
            )
        }
    }

    fun executeRunBlocking() {
        Log.d("ThirdScreenViewModel", "before runBlocking")
        runBlocking {
            Log.d("ThirdScreenViewModel", "start runBlocking")
            delay(5000)
            Log.d("ThirdScreenViewModel", "End runBlocking")
        }
        Log.d("ThirdScreenViewModel", "After runBlocking")
    }

    fun executeWithContext() {
        Log.d("ThirdScreenViewModel", "Before With Context")

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("ThirdScreenViewModel", "start With Context")
                delay(5000)
                Log.d("ThirdScreenViewModel", "End With Context")
            }
        }

        Log.d("ThirdScreenViewModel", "After With Context")
    }

}