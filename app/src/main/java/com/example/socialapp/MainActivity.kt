package com.example.socialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialapp.domain.usecase.SignInUseCase
import com.example.socialapp.domain.usecase.SignUpUseCase
import com.example.socialapp.presentation.nav.NavRoutes
import com.example.socialapp.presentation.splash.SplashScreen
import com.example.socialapp.presentation.splash.SplashViewModel
import com.example.socialapp.presentation.signin.SignInScreen
import com.example.socialapp.presentation.signin.SignInViewModel
import com.example.socialapp.presentation.signup.SignUpScreen
import com.example.socialapp.presentation.signup.SignUpViewModel
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.socialapp.presentation.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = (application as SocialApp).container

        // الـ ViewModels مسؤولة عن منطق التطبيق لكل شاشة:
        val splashVM = SplashViewModel(container.authRepository)
        val signInVM = SignInViewModel(SignInUseCase(container.authRepository))
        val signUpVM = SignUpViewModel(SignUpUseCase(container.authRepository))

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = NavRoutes.Splash
            ) {
                composable(NavRoutes.Splash) {
                    SplashScreen(
                        vm = splashVM,
                        onGoHome = { navController.navigate(NavRoutes.Home) },
                        onGoSignIn = { navController.navigate(NavRoutes.SignIn) }
                    )
                }

                composable(NavRoutes.SignIn) {
                    SignInScreen(
                        vm = signInVM,
                        onGoHome = { navController.navigate(NavRoutes.Home) },
                        onGoSignUp = { navController.navigate(NavRoutes.SignUp) }
                    )
                }

                composable(NavRoutes.SignUp) {
                    SignUpScreen(
                        vm = signUpVM,
                        onGoHome = { navController.navigate(NavRoutes.Home) },
                        onGoSignIn = { navController.navigate(NavRoutes.SignIn) }
                    )
                }

                composable(NavRoutes.Home) {
                   MainScreen(navController)

                }
            }
        }
    }
}
