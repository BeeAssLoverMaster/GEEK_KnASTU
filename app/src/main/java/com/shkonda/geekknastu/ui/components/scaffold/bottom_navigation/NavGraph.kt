package com.shkonda.geekknastu.ui.components.scaffold.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shkonda.geekknastu.screens.EventScreen
import com.shkonda.geekknastu.screens.HomeScreen
import com.shkonda.geekknastu.screens.InfoScreen
import com.shkonda.geekknastu.screens.PersonScreen
import com.shkonda.geekknastu.util.ListItem
import com.shkonda.geekknastu.util.Routes

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    var item: ListItem? = null
    val mainList = remember {
        mutableListOf(item)
    }
    NavHost(navHostController, startDestination = "Home") {
        composable("Home") {
            HomeScreen()
        }
        composable("Events") {
            EventScreen() { listItem ->
                item = listItem
                navHostController.navigate(Routes.INFO_SCREEN)
            }
        }
        composable("Profile") {
            PersonScreen()
        }
        composable(Routes.INFO_SCREEN) {
            InfoScreen(item = item!!)
        }
    }
}