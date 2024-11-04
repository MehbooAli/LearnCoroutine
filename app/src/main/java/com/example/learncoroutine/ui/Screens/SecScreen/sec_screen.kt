package com.example.learncoroutine.ui.Screens.SecScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learncoroutine.Navigation.NavigationItem
import com.example.learncoroutine.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sec_screen(navController: NavController) {
    val secScreenViewModel: SecScreenViewModel = hiltViewModel()
    val posts1 = secScreenViewModel.posts1.value
    val posts2 = secScreenViewModel.posts2.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("This is Second Page") },
                navigationIcon = {
                    Image(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }) {
        Column(modifier = Modifier.padding(it).fillMaxSize()) {
            Button(onClick = { navController.navigate(NavigationItem.ThirdPage.route) }) {
                Text("Go Third Page")
            }

            LazyColumn {
                items(posts1) { items ->
                    Text(text = items.title)
                }
            }

            LazyColumn {
                items(posts2) { item ->
                    Text("item+++++++++++++++++++++++++: ${item.title}")
                }
            }
        }

    }
}
