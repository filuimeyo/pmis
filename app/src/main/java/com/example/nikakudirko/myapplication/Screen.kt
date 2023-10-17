package com.example.nikakudirko.myapplication

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.nikakudirko.myapplication.MemoriesDestinationsArgs.MEMORY_ID_ARG
import com.example.nikakudirko.myapplication.MemoriesDestinationsArgs.TITLE_ARG
import com.example.nikakudirko.myapplication.MemoriesDestinationsArgs.USER_MESSAGE_ARG
import com.example.nikakudirko.myapplication.PlanetsScreens.ADD_EDIT_MEMORYHOME_SCREEN
import com.example.nikakudirko.myapplication.PlanetsScreens.HOME_SCREEN
import java.util.UUID

const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3

private object PlanetsScreens {
    const val HOME_SCREEN = "home"
    const val ADD_EDIT_MEMORYHOME_SCREEN = "new"
}
object MemoriesDestinationsArgs {
    const val MEMORY_ID_ARG = "id"
    const val TITLE_ARG = "title"
    const val USER_MESSAGE_ARG = "userMessage"
}
object MemoriesDestinations {
    const val ADD_EDIT_MEMORY_ROUTE = "$ADD_EDIT_MEMORYHOME_SCREEN/{$TITLE_ARG}?$MEMORY_ID_ARG={$MEMORY_ID_ARG}"

}
class MemoriesNavigationActions(private val navController: NavHostController) {
    fun navigateToAddEditMemory( title: Int, id: UUID?) {

        navController.navigate(
            "${ADD_EDIT_MEMORYHOME_SCREEN}/$title".let {
                //  System.out.println(if (id != null) "$it?$MEMORY_ID_ARG=$id" else it)
                if (id != null) "$it?$MEMORY_ID_ARG=$id" else it
            }
        )
    }

    fun navigateToMemories(userMessage: Int = 0) {
        val navigatesFromDrawer = userMessage == 0
        navController.navigate(
            HOME_SCREEN.let {
                if (userMessage != 0) "$it?$USER_MESSAGE_ARG=$userMessage" else it
            }
        ) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = !navigatesFromDrawer
                saveState = navigatesFromDrawer
            }
            launchSingleTop = true
            restoreState = navigatesFromDrawer
        }
    }
}

sealed class Screen(val route: String){
    object HomeScreen: Screen("home")
    object EditScreen: Screen("new")
    object AboutScreen: Screen("about")


    fun withArgs(vararg args: String) : String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}
