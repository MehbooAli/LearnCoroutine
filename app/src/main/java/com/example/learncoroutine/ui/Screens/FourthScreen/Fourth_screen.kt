package com.example.learncoroutine.ui.Screens.FourthScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Fourth_screen(navController: NavController) {
    val fourthScreenViewModel: FourthScreenViewModel = hiltViewModel()

    Scaffold(topBar = { TopAppBar(title = { Text("Fourth Screen") }) }) {

        Column(modifier = Modifier.padding(it)) {
            Text("Learn Job Cancelled, timeout execution, ")

            Button(onClick = { fourthScreenViewModel.executeFstFun() }) {
                Text("Test job functionality")
            }

            Button(onClick = { fourthScreenViewModel.executeSecFun() }) {
                Text("canceled Job")
            }

            Button(onClick = { fourthScreenViewModel.executeThirdFun() }) {
                Text("Time Out Request Text")
            }

            Text("Learn Async and Await")
            Button(onClick = { fourthScreenViewModel.printNetworkCall() }) {
                Text("Test NetWork Call")
            }

            Text(text = fourthScreenViewModel.networkData1.value)
            Text(text = fourthScreenViewModel.networkData2.value)

        }
    }

}