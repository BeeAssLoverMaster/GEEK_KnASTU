package com.shkonda.geekknastu.ui.components.scaffold.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shkonda.geekknastu.screens.EventScreen
import com.shkonda.geekknastu.screens.HomeScreen
import com.shkonda.geekknastu.screens.PersonScreen

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