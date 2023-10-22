package com.shkonda.geekknastu.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shkonda.geekknastu.Screens.EventScreen
import com.shkonda.geekknastu.Screens.HomeScreen
import com.shkonda.geekknastu.Screens.PersonScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navHostController, startDestination = "Home") {
        composable("Home") {
            HomeScreen()
        }
        composable("Events") {
            EventScreen()
        }
        composable("Profile") {
            PersonScreen()
        }
    }
}