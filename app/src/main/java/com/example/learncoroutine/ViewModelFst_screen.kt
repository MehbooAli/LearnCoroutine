package com.example.learncoroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class ViewModelFst_screen() : ViewModel() {
    val a = "Hello word"

    fun doWork() {
        // Just one method for launch coroutine viewModelScope
        viewModelScope.launch {
            delay(5000)
            Log.d("ViewModelFst", "Coroutine Scope do work fun:  ${Thread.currentThread().name}")
        }
    }

    fun CoroutineTestFun() {
        CoroutineScope(Dispatchers.Default).launch {
            Log.d("ViewModelFst", "Coroutine Scope Dispatcher Main ${Thread.currentThread().name}")
        }

    }

    fun yieldLaunch() {
        viewModelScope.launch {
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
        viewModelScope.launch {
            printFbInstagramFollower()
        }
    }


    // Launch method mostly we use for I did not find any
    private suspend fun printFbInstagramFollower() {
        var fbfol = 0
        var instafol = 0

        CoroutineScope(Dispatchers.IO).launch {
            val fb = async {   getFacebookFollower()}
            val insta = async {  getInstagramFollowers()}

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
}