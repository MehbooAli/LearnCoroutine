package com.example.learncoroutine.ui.Screens.ThirdScreen

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
import com.example.learncoroutine.Navigation.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Third_Screen(navController: NavController) {
    val thirdScreenViewModel: ThirdScreenViewModel = hiltViewModel()

    Scaffold(topBar = { TopAppBar(title = { Text("Third Page") }) }) {
        Column(modifier = Modifier.padding(it)) {
            Text(thirdScreenViewModel.a.value)
            Text(thirdScreenViewModel.b.value)
            Button(onClick = { thirdScreenViewModel.executeRunBlocking() }) {
                Text("Test Run Blocking")
            }
            Button(onClick = { thirdScreenViewModel.executeWithContext() }) {
                Text("Execute With Context")
            }

            Button(onClick = { navController.navigate(NavigationItem.FourthPage.route) }
            ) {
                Text("Go Fourth page")
            }
        }
    }
}