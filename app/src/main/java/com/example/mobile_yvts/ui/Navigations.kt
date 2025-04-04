package com.example.mobile_yvts.ui

sealed class Navigations(val route: String) {
    object Home : Navigations("home")
    object Add : Navigations("add")
    object Edit : Navigations("edit/{wordId}") {
        fun createRoute(wordId: Int) = "edit/$wordId"
    }
    object Settings : Navigations("settings")
}
