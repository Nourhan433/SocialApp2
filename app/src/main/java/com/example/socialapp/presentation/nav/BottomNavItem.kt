package com.example.socialapp.presentation.nav

import androidx.annotation.DrawableRes
import com.example.socialapp.R // you will create icons in res/drawable

data class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
)

val bottomNavItems = listOf(
    BottomNavItem(NavRoutes.Main, "Home", R.drawable.ic_home),
    BottomNavItem(NavRoutes.Profile, "Profile", R.drawable.ic_profile),
    BottomNavItem(NavRoutes.Settings, "Settings", R.drawable.ic_settings),
)