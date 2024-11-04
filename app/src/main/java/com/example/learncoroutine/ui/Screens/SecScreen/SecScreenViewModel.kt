package com.example.learncoroutine.ui.Screens.SecScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncoroutine.Data.Api.RetrofitInstance
import com.example.learncoroutine.Data.Model.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SecScreenViewModel @Inject constructor() : ViewModel() {
    private val apiService = RetrofitInstance.api
    val posts1: MutableState<List<PostModel>> = mutableStateOf(emptyList())
    val posts2: MutableState<List<PostModel>> = mutableStateOf(emptyList())

    init {
//        viewModelScope.launch {
//            loadData1()
//            loadData2()
//        }

//        getPosts1()

        viewModelScope.launch {
            getPosts1()
            getPost2()
        }

    }


    suspend fun getPosts1() {
        Log.e("SecScreenViewModel", "Get post thread posts: ${Thread.currentThread().name}")

        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPost()
                if (response.isNotEmpty()) {
                    posts1.value = response

                    Log.d("SecScreenViewModel", "Fetched Posts: ${posts1.value}")

                } else {
                    Log.d("SecScreenViewModel", "No posts available")
                }
            } catch (e: Exception) {
                // Handle errors here
                Log.e("SecScreenViewModel", "Error fetching posts: ${e.localizedMessage}")

            }
        }
    }


    suspend fun getPost2() {
        Log.d("SecScreenViewModel", "Get post 2 fun: ${Thread.currentThread().name}")

        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPost()
                if (response.isNotEmpty()) {
                    posts2.value = response

                } else {
                    Log.d("SecScreenViewModel", "No Data available of get Post Two.")
                }
            } catch (e: Exception) {
                Log.e("SecScreenViewModel", "Error get post fun 2: ${e.localizedMessage}")
            }
        }

    }

    // 1
    // Launched in the main dispatcher (UI thread)
    suspend fun loadData1() {

        // Switch to IO dispatcher for the network call
        val data = withContext(Dispatchers.IO) {
            Log.e("SecScreenViewModel", "Load data posts: ${Thread.currentThread().name}")

            try {
                // Fetch data from network
                val response = apiService.getPost()
                if (response.isNotEmpty()) {
                    response
                } else {
                    emptyList() // Return empty list if no data
                }
            } catch (e: Exception) {
                Log.e("SecScreenViewModel", "Error fetching posts: ${e.localizedMessage}")
                emptyList() // Return empty list on error
            }
        }

        // Back on the main thread to update UI
        updateDataUI1(data)
    }

    /**
     * Updates posts state on the main thread.
     */
    private fun updateDataUI1(data: List<PostModel>) {
        Log.e("SecScreenViewModel", "Update UI posts: ${Thread.currentThread().name}")

        posts1.value = data
        Log.d("SecScreenViewModel", "Updated Posts in UI: ${posts1.value}")
    }

    // 2
    // Launched in the main dispatcher (UI thread)
    suspend fun loadData2() {

        // Switch to IO dispatcher for the network call
        val data = withContext(Dispatchers.IO) {
            Log.e("SecScreenViewModel", "Load data posts: ${Thread.currentThread().name}")

            try {
                // Fetch data from network
                val response = apiService.getPost()
                if (response.isNotEmpty()) {
                    response
                } else {
                    emptyList() // Return empty list if no data
                }
            } catch (e: Exception) {
                Log.e("SecScreenViewModel", "Error fetching posts: ${e.localizedMessage}")
                emptyList() // Return empty list on error
            }
        }

        // Back on the main thread to update UI
        updateDataUI2(data)
    }

    /**
     * Updates posts state on the main thread.
     */
    private fun updateDataUI2(data: List<PostModel>) {
        Log.e("SecScreenViewModel", "Update UI posts: ${Thread.currentThread().name}")

        posts1.value = data
        Log.d("SecScreenViewModel", "Updated Posts in UI: ${posts1.value}")
    }

}