package com.example.learncoroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.learncoroutine.ui.theme.LearnCoroutineTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnCoroutineTheme {
                fst_screen(viewmodelfstScreen = ViewModelFst_screen())
//                }
            }
        }

        // delay with only pause the current coroutine and it will not block the whole thread
        // Run Coroutine
        GlobalScope.launch {
            delay(3000)
            Log.d("MainActivity", "Coroutine says hell from thread ${Thread.currentThread().name}")
            Log.d("MainActivity", "Coroutine says hell from thread ${doNetworkCall()}")
            DisIoThread()
        }
        Log.d("MainActivity", "Hello from thread ${Thread.currentThread().name}")

    }

    private suspend fun doNetworkCall(): String {
        delay(5000)
        return "This is the answer"
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun DisIoThread() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("MainActivity", "Coroutine main : ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("MainActivity", "Coroutine Default io : ${Thread.currentThread().name}")
        }
        CoroutineScope(Dispatchers.Default).launch {
            Log.d("MainActivity", " Coroutine Dispatcher default : ${Thread.currentThread().name}")

        }
        CoroutineScope(Dispatchers.Unconfined).launch {
            Log.d(
                "MainActivity",
                "Coroutine Dispatcher Unconfined : ${Thread.currentThread().name}"
            )
        }

//        Using GlobalScope for Application-Wide Tasks (Use Cautiously)
        GlobalScope.launch(Dispatchers.Main) {
            Log.d("MainActivity", "Global Scope Main : ${Thread.currentThread().name}")
        }

        MainScope().launch(Dispatchers.Unconfined) {
            Log.d("MainActivity", "MainScople Default : ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.IO) {
            Log.d("MainActivity", "GlobalScope Dispatcher Io : ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.Default) {
            Log.d(
                "MainActivity",
                "Global Scope Dispatcher Default : ${Thread.currentThread().name}"
            )
        }

        // In an Activity or Fragment
        lifecycleScope.launch {
            Log.d("MainActivity", "GlobalScope Dispatcher Io : ${Thread.currentThread().name}")
        }

        lifecycleScope.launch {
            execute()
        }

        MainScope().launch {
            executeTask()
        }

        lifecycleScope.launch {
            runBlockingExecute()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun execute() {
        val parentJob = GlobalScope.launch(Dispatchers.Main) {

            Log.d("MainActivity", "Parent Started")


            val childJob = launch(Dispatchers.IO) {
                try {
                    Log.d("MainActivity", "Child job Started")
                    delay(5000)
                    Log.d("MainActivity", "Child job Ended")
                } catch (e: Exception) {
                    Log.d("MainActivity", "Child job Cancelled")
                }
            }

            delay(3000)
            childJob.cancel()
            Log.d("MainActivity", "Parent Ended")
        }

        parentJob.join()
        Log.d("MainActivity", "Parent Completed")
    }


    private suspend fun executeTask() {
        Log.d("MainActivity", "Before")

        withContext(Dispatchers.Main) {
            delay(1000)
            Log.d("MainActivity", "Inside")
        }

        Log.d("MainActivity", "After")
    }

    // In Simple work runBlocking when coroutine all code execute then application is closed other vise not
    private suspend fun runBlockingExecute() {
        runBlocking {
            launch {
                delay(6000)
                Log.d("MainActivity", "runBlocking fun Execute: ${Thread.currentThread().name}")
            }

            Log.d("MainActivity", "After Run Blocking ${Thread.currentThread().name}")
        }
    }
}
