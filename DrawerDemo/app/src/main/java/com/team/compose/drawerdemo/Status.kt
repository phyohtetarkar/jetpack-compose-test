package com.team.compose.drawerdemo

import androidx.compose.Model

sealed class Screen {
    object Home : Screen()
    object List : Screen()
}

@Model
object DrawerDemoStatus {
    var currentScreen: Screen = Screen.Home
}

fun navigateTo(destination: Screen) {
    DrawerDemoStatus.currentScreen = destination
}