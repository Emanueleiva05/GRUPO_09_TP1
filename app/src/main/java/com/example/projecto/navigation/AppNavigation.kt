//Aca se define la navegacion entre las pantallas. Se agregan las rutas y se asocian.

package com.example.projecto.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecto.ui.screens.RegisterScreen
import com.example.projecto.ui.screens.LoginScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
    }
}
