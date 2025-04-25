//Aca se define la navegacion entre las pantallas. Se agregan las rutas y se asocian.

package com.example.projecto.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projecto.data.UserViewModel
import com.example.projecto.ui.screens.RegisterScreen
import com.example.projecto.ui.screens.LoginScreen
import com.example.projecto.ui.screens.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    // Crea una única instancia del ViewModel que se compartirá
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("register") {
            RegisterScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }
        composable("login") {
            LoginScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }
        composable("home") {
            HomeScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }
    }
}