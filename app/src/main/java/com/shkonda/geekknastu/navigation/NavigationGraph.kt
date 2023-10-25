package com.shkonda.geekknastu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shkonda.geekknastu.screens.login_screen.SignInScreen
import com.shkonda.geekknastu.screens.signup_screen.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignInScreen.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController)
        }
    }

}