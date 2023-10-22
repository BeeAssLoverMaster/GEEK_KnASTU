package com.shkonda.geekknastu.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.shkonda.geekknastu.R
import com.shkonda.geekknastu.Screen

sealed class BottomItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    object Home: BottomItem("Home", Icons.Filled.Home,
        Icons.Outlined.Home,
        "home")
    object Events: BottomItem("Events", Icons.Filled.Event,
        Icons.Outlined.Event,
        "events")
    object Profile: BottomItem("Profile", Icons.Filled.Person,
        Icons.Outlined.Person,
        "profile")
}
