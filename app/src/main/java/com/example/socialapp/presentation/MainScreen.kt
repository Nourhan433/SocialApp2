package com.example.socialapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import com.example.socialapp.presentation.nav.NavRoutes
import com.example.socialapp.presentation.nav.bottomNavItems


@Composable
fun MainScreen(rootNavController: NavController) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(NavRoutes.Main) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(painterResource(id = item.icon), contentDescription = item.label,
                            modifier = Modifier.size(24.dp)) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Main,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.Main) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Welcome Home!")

                    Spacer(Modifier.height(10.dp))

                    Button(onClick = {
                        // Logout: use root navController (not inner one)
                        rootNavController.navigate(NavRoutes.SignIn) {
                            popUpTo(NavRoutes.Home) { inclusive = true }
                        }
                    }) {
                        Text("Logout")
                    }
                }
            }
            composable(NavRoutes.Profile) { ProfileScreen() }
            composable(NavRoutes.Settings) { SettingsScreen() }
        }
    }
}
