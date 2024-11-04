package com.example.learncoroutine.Navigation

import kotlinx.serialization.Serializable

//@Serializable
//sealed class Screen(val route: String) {
//    @Serializable
//    object Main: Screen("main_screen")
//    object Detail: Screen("detail_screen")
//
//}



@Serializable
enum class Screen {
    HOME,
    FSTPAGE,
    SECPAGE,
    THIRDPAGE,
    FOURTHPAGE,
    FIFTHPAGE,
}

@Serializable
sealed class NavigationItem(val route: String) {
//    @Serializable
//    object Home : NavigationItem(Screen.HOME.name)

    @Serializable
    object FstPage : NavigationItem(Screen.FSTPAGE.name)

    @Serializable
    object SecPage : NavigationItem(Screen.SECPAGE.name)

    @Serializable
    object ThirdPage : NavigationItem(Screen.THIRDPAGE.name)

    @Serializable
    object FourthPage : NavigationItem(Screen.FOURTHPAGE.name)

    @Serializable
    object FifthPage : NavigationItem(Screen.FIFTHPAGE.name)
}
