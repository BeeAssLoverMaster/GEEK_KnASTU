package com.shkonda.geekknastu.navigation

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shkonda.geekknastu.Screens.HomeScreen
import com.shkonda.geekknastu.Screens.signup_screen.SignUpScreen
import com.shkonda.geekknastu.SignInScreen

@Composable
fun NavigationGraph(
    sharedPreferences: SharedPreferences,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignInScreen.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(sharedPreferences, navController)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController)
        }
    }

}