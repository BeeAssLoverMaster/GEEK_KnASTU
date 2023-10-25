package com.shkonda.geekknastu.ui.components.scaffold.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    object Home: BottomItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = "home")
    object Events: BottomItem("Events", Icons.Filled.Event,
        Icons.Outlined.Event,
        "events")
    object Profile: BottomItem("Profile", Icons.Filled.Person,
        Icons.Outlined.Person,
        "profile")
}
