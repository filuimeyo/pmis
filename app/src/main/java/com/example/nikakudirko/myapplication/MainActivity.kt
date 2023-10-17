@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.nikakudirko.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AddCircleOutline
//import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nikakudirko.myapplication.MemoriesDestinationsArgs.MEMORY_ID_ARG
import com.example.nikakudirko.myapplication.screens.AboutScreen
import com.example.nikakudirko.myapplication.screens.EditScreen
import com.example.nikakudirko.myapplication.screens.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        val aboutMeStrings = listOf(
            R.string.fact1,
            R.string.fact2,
            R.string.fact3

        )

    }


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            val items = listOf(
                BottomNavigationItem(
                    title = "home",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    hasNews = false,
                ),
                BottomNavigationItem(
                    title = "new",
                    selectedIcon = Icons.Filled.AddCircle,
                    unselectedIcon = Icons.Outlined.AddCircleOutline,
                    hasNews = false,

                    ),
                BottomNavigationItem(
                    title = "about",
                    selectedIcon = Icons.Filled.Info,
                    unselectedIcon = Icons.Outlined.Info,
                    hasNews = false,
                    //  badgeCount = 23,

                ),
            )
            var selectedItemIndex by rememberSaveable {
                mutableIntStateOf(0)
            }

            val navController = rememberNavController()

            val navActions: MemoriesNavigationActions = remember(navController) {
                MemoriesNavigationActions(navController)
            }

            Scaffold(
                // Modifier.background(color = colorResource(id = R.color.navbar_color)),

                bottomBar = {
                    NavigationBar(
                        containerColor = colorResource(id = R.color.navbar_color),
                        contentColor = colorResource(id = R.color.navbar_item)
                    ) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(

                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = colorResource(id = R.color.navbar_item)
                                ),

                                selected = currentDestination?.hierarchy?.any { it.route == item.title } == true,
                                onClick = {

                                    selectedItemIndex = index


                                    if (index == 1) {
                                        navController.navigate(Screen.EditScreen.withArgs(null.toString())) {
                                            launchSingleTop = true //?????
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            restoreState = true
                                        }
                                    } else {
                                        navController.navigate(item.title) {
                                            launchSingleTop = true //?????
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            restoreState = true
                                        }
                                    }


                                },
                                label = {
                                    Text(text = item.title)
                                },
                                alwaysShowLabel = false,
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (item.badgeCount != null) {
                                                Badge {
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            } else if (item.hasNews) {
                                                Badge()
                                            }

                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    }
                                }
                            )
                        }
                    }
                },
                content = {
                    NavHost(
                        navController = navController, startDestination = Screen.HomeScreen.route,

                        ) {


                        composable(Screen.HomeScreen.route) {
                            HomeScreen(navController)
                        }
                        composable(
                            // route = Screen.EditScreen.route + "/{articleId}",
                            route = MemoriesDestinations.ADD_EDIT_MEMORY_ROUTE,
                            arguments = listOf(
                                navArgument(MEMORY_ID_ARG) {
                                    type = NavType.StringType
                                    defaultValue = null
                                    nullable = true
                                }
                            )
                        ) { entry ->

                            // EditScreen(navController, entry.arguments?.getString("articleId"))
                            val memoryId = entry.arguments?.getString(MEMORY_ID_ARG)
                            EditScreen(
                                navController,
                                onMemoryUpdate = {
                                    navActions.navigateToMemories(
                                        if (memoryId == null) ADD_EDIT_RESULT_OK else EDIT_RESULT_OK
                                    )
                                },
                            )

                        }
                        composable(Screen.AboutScreen.route) {
                            AboutScreen()
                        }
                    }
                }
            )
        }
    }
}
