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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialapp.presentation.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = NavRoutes.Splash
            ) {
                composable(NavRoutes.Splash) {
                    val splashVM: SplashViewModel= hiltViewModel()
                    SplashScreen(
                        vm = splashVM,
                        onGoHome = { navController.navigate(NavRoutes.Home) },
                        onGoSignIn = { navController.navigate(NavRoutes.SignIn) }
                    )
                }

                composable(NavRoutes.SignIn) {
                    val signInVM: SignInViewModel = hiltViewModel()
                    SignInScreen(
                        vm = signInVM,
                        onGoHome = { navController.navigate(NavRoutes.Home) },
                        onGoSignUp = { navController.navigate(NavRoutes.SignUp) }
                    )
                }

                composable(NavRoutes.SignUp) {
                     val signUpVM: SignUpViewModel = hiltViewModel()
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
