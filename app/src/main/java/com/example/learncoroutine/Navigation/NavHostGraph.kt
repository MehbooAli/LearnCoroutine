package com.example.learncoroutine.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.learncoroutine.ui.Screens.FourthScreen.Fourth_screen
import com.example.learncoroutine.ui.Screens.FstScreen.fst_screen
import com.example.learncoroutine.ui.Screens.SecScreen.sec_screen
import com.example.learncoroutine.ui.Screens.ThirdScreen.Third_Screen


@Composable
fun NavHostGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationItem.FstPage.route) {

        composable(route = NavigationItem.FstPage.route) { fst_screen(navController) }
        composable(route = NavigationItem.SecPage.route) { sec_screen(navController) }
        composable(route = NavigationItem.ThirdPage.route) { Third_Screen(navController) }
        composable(route = NavigationItem.FourthPage.route) { Fourth_screen(navController)  }
    }

}




