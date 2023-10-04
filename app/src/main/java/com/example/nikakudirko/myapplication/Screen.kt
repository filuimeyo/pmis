package com.example.nikakudirko.myapplication


sealed class Screen(val route: String){
    object HomeScreen: Screen("home")
    object EditScreen: Screen("new")
    object AboutScreen: Screen("about")
}
