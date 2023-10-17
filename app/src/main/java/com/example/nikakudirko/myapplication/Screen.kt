package com.example.nikakudirko.myapplication


sealed class Screen(val route: String){
    object HomeScreen: Screen("home")
    object EditScreen: Screen("new")
    object AboutScreen: Screen("about")


    fun withArgs(vararg args: String) : String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("?articleId = $arg")
            }
        }
    }
}
