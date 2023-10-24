package com.shkonda.geekknastu.navigation

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shkonda.geekknastu.Screens.login_screen.SignInScreen
import com.shkonda.geekknastu.Screens.signup_screen.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    sharedPreferences: SharedPreferences
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
//        composable(route = Screens.AllScreens.route) {
//            AllScreens()
//        }
    }

}