package com.example.learncoroutine.ui.Screens.FstScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncoroutine.Data.Api.RetrofitInstance
import com.example.learncoroutine.Data.Model.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import javax.inject.Inject

//class ViewModelFst_screen(private val navController: NavController) : ViewModel() {

@HiltViewModel
class FstScreenViewModel @Inject constructor() : ViewModel() {

    val a = "Hello word"
    var fstString = mutableStateOf("Hi")
    var secString = mutableStateOf("")

    init {
        printFstString()
        printSecString()
    }

    fun doWork() {
        // Just one method for launch coroutine viewModelScope
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            Log.d("ViewModelFst", "Coroutine Scope do work fun:  ${Thread.currentThread().name}")
        }
    }

    fun CoroutineTestFun() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("ViewModelFst", "Coroutine Scope Dispatcher Main ${Thread.currentThread().name}")
        }

    }

    fun yieldLaunch() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            testFunYield()
        }
    }

    private suspend fun testFunYield() {
        yield()
        Log.d("ViewModelFst", "Test Fun with yield like a delay : ${Thread.currentThread().name}")
    }

    fun printFoller() {
        viewModelScope.launch {
            printFollowers()
        }
    }

    // Job.join() for suspend coroutine function

    private suspend fun printFollowers() {
        var fbFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            fbFollowers = getFbFollors()
        }

        job.join()
        Log.d("ViewModelFst", fbFollowers.toString())
    }

    private suspend fun getFbFollors(): Int {
        delay(1000)
        return 54
    }

    fun asynicPrint() {
        viewModelScope.launch {
            asynicFun()
        }
    }

    suspend fun asynicFun() {
        val job = CoroutineScope(Dispatchers.IO).async {
            asynicFollower()
        }

        Log.d("ViewModelFst", job.await().toString())
    }

    private suspend fun asynicFollower(): Int {
        delay(1000)
        return 50
    }

    fun printFbInstagramFol() {
        viewModelScope.launch(Dispatchers.IO) {
            printFbInstagramFollower()
        }
    }


    // Launch method mostly we use for I did not find any
    private suspend fun printFbInstagramFollower() {
        var fbfol = 0
        var instafol = 0

        CoroutineScope(Dispatchers.IO).launch {
            val fb = async { getFacebookFollower() }
            val insta = async { getInstagramFollowers() }

            Log.d("ViewModelFst", "Get Data: ${fb.await()} and instagram: ${insta.await()}")
        }

        val fb = CoroutineScope(Dispatchers.IO).launch {
            fbfol = getFacebookFollower()
        }

        val instagram = CoroutineScope(Dispatchers.IO).launch {
            instafol = getInstagramFollowers()
        }

        fb.join()
        instagram.join()
        Log.d("ViewModelFst", "print fb and instagram: ${fbfol} followers: ${instafol}")

    }

    private suspend fun getFacebookFollower(): Int {
        delay(1000)
        return 23
    }

    private suspend fun getInstagramFollowers(): Int {
        delay(1000)
        return 44
    }

    private suspend fun getLInkdinFol() {
        delay(2000)

    }

    fun printFstString() {
        viewModelScope.launch {
            fstString.value = getFstString()
        }
    }

    private suspend fun getFstString(): String {
        delay(5000)
        return "This is first String from Coroutine."
    }

    private fun printSecString() {
        viewModelScope.launch {
            secString.value =  getSecString()
        }
    }

    private suspend fun getSecString(): String{
        delay(3000)
        return "This is second String form Coroutine."
    }

}