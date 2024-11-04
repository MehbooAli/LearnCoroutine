package com.example.learncoroutine.ui.Screens.FstScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
//fun fst_screen(navController: NavController) {
fun fst_screen(navController: NavController) {

    val viewmodelfstScreen: FstScreenViewModel = hiltViewModel()

    Scaffold(topBar = { TopAppBar(title = { Text("This is Title") }) }) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Text("This is content value.")
            Text(viewmodelfstScreen.a)
            viewmodelfstScreen.doWork()
            viewmodelfstScreen.CoroutineTestFun()
            viewmodelfstScreen.yieldLaunch()
            viewmodelfstScreen.printFoller()
            viewmodelfstScreen.asynicPrint()
            viewmodelfstScreen.printFbInstagramFol()
            Row {
                Checkbox(true, onCheckedChange = {}, modifier = Modifier, enabled = true)
                Text("This is Check Box")
            }

            Button(onClick = { navController.navigate(NavigationItem.SecPage.route) }) {
                Text("Go Second Page")
            }

            Text(text = viewmodelfstScreen.fstString.value)
            Text(text = viewmodelfstScreen.secString.value)
        }
    }

}