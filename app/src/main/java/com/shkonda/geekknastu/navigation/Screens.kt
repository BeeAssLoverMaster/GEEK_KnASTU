package com.shkonda.geekknastu.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object AllScreens : Screens(route = "All_Screens")
}