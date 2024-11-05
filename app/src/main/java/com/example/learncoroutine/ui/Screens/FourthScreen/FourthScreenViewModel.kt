package com.example.learncoroutine.ui.Screens.FourthScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.system.measureTimeMillis

class FourthScreenViewModel @Inject constructor() : ViewModel() {
    var networkData1 = mutableStateOf("fst")
    var networkData2 = mutableStateOf("sec")

    @OptIn(DelicateCoroutinesApi::class)
    fun executeFstFun() {
        val job1 = GlobalScope.launch(Dispatchers.Default) {
            repeat(5) {
                Log.d("FourthScreenViewModel", "Execution start...")
                delay(1000)
            }
        }

        runBlocking {
            Log.d("FourthScreenViewModel", "Main Thread")
            delay(3000)
            job1.cancel()
            Log.d("FourthScreenViewModel", "Go on main thread.")
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun executeSecFun() {
        val job2 = GlobalScope.launch(Dispatchers.Default) {
            Log.d("FourthScreenViewModel", "Start long running calculation...")

            for (i in 30..40) {
                if (isActive) {
                    Log.d("FourthScreenViewModel", "Result for i = $i : ${fib(i)}")
                }
            }

            Log.d("FourthScreenViewModel", "Ending Long running calculation...")
        }

        runBlocking {
            delay(2000)
            job2.cancel()
            Log.d("FourthScreenViewModel", "Canceled Job")
        }

    }


    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun executeThirdFun() {
        val job2 = GlobalScope.launch(Dispatchers.Default) {
            Log.d("FourthScreenViewModel", "Start long running calculation...")

            withTimeout(2000) {
                for (i in 30..40) {
                    if (isActive) {
                        Log.d("FourthScreenViewModel", "Result for i = $i : ${fib(i)}")
                    }
                }
            }

            Log.d("FourthScreenViewModel", "Ending Long running calculation...")
        }

    }

    fun printNetworkCall() {
        val time = measureTimeMillis {
            viewModelScope.launch {
                val a = async { networkCallFst() }
                val b = async { networkCallSecond() }
                networkData1.value = b.await()
                networkData2.value = b.await()

//                networkData1.value = networkCallFst()
//                networkData2.value = networkCallSecond()

            }
        }

        Log.d("FourthScreenViewModel", "Measure Request Time: $time")
    }


    suspend fun networkCallFst(): String {
        delay(3000)
        return "This is first network Call"
    }

    suspend fun networkCallSecond(): String {
        delay(3000)
        return "This is second network Call"
    }

}