package com.example.projecto.ui.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.text.Layout
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projecto.R
import com.example.projecto.data.User
import com.example.projecto.data.UserViewModel
import com.example.projecto.ui.theme.Primario
import com.example.projecto.ui.theme.Texto
import com.example.projecto.ui.theme.TextoSecundario
import com.example.projecto.ui.theme.Fondo
import com.example.projecto.ui.theme.Secundario
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController, userViewModel: UserViewModel) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordTwo by remember { mutableStateOf("") }

    var nombreError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var showCancelDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ){
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Fondo,
                                Fondo,
                                Primario
                            ),
                            startY = 0.5f * LocalDensity.current.density,
                            endY = LocalDensity.current.density * 1000f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .padding(24.dp)
                    .shadow(elevation = 16.dp, shape = RoundedCornerShape(16.dp))
                    .background(Fondo, shape = RoundedCornerShape(16.dp))
                    .align(Alignment.Center)
            ) {
                Text(
                    color = Primario,
                    text = "Bienvenido a nuestra App.",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .align(Alignment.TopCenter)
                )
                Column {
                    Spacer(modifier = Modifier.height(136.dp))
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small,
                        textStyle = TextStyle(Primario)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small,
                        textStyle = TextStyle(Primario)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small,
                        textStyle = TextStyle(Primario),
                        visualTransformation = PasswordVisualTransformation(),  // Esto oculta el texto
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)  // Teclado adecuado
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = passwordTwo,
                        onValueChange = { passwordTwo = it },
                        label = { Text("Repetir contraseña", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small,
                        textStyle = TextStyle(Primario),
                        visualTransformation = PasswordVisualTransformation(),  // Esto oculta el texto
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)  // Teclado adecuado
                    )
                    Spacer(modifier = Modifier.height(130.dp))

                    Button(
                        onClick = {
                            val isEmailValid = email.isNotBlank() && email.contains("@")
                            val isPasswordValid = password.length >= 6 && password == passwordTwo
                            val isNombreValid = nombre.isNotBlank()

                            nombreError = !isNombreValid
                            emailError = !isEmailValid
                            passwordError = !isPasswordValid

                            if (isNombreValid && isEmailValid && isPasswordValid) {

                                showConfirmDialog = true

                            } else {
                                coroutineScope.launch{
                                    snackbarHostState.showSnackbar("Revisa los campos ingresados: \n" +
                                            "${if (!isNombreValid) "Nombre\n" else ""}" +
                                            "${if (!isEmailValid) "Email\n" else ""}" +
                                            "${if (!isPasswordValid) "Contraseña" else ""}",
                                    duration = SnackbarDuration.Short)
                                }
                            }
                        },
                        modifier = Modifier
                            .height(65.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp),
                        shape = MaterialTheme.shapes.medium,
                        border = BorderStroke(1.dp, Primario),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Fondo,
                        ),
                        contentPadding = PaddingValues(),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 16.dp)
                    ) {
                        Text("Registrarme", color = Texto)
                    }

                    Button(
                        onClick = {
                            showCancelDialog = true
                        },
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFFFF0101)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Fondo,
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 16.dp)
                    ) {
                        Text("Cancelar", color = Texto)
                    }

                }
            }
            if (showCancelDialog) {
                AlertDialog(
                    onDismissRequest = { showCancelDialog = false },
                    title = {
                        Text(
                            text = "¿Cancelar registro?",
                            style = MaterialTheme.typography.titleMedium,
                            color = Primario
                        )
                    },
                    text = {
                        Text(
                            text = "Se perderán todos los datos ingresados.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextoSecundario
                        )
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Fondo,
                            ),
                            border = BorderStroke(1.dp, Color(0xFFFF0101)),
                            onClick = {
                                nombre = ""
                                email = ""
                                password = ""
                                passwordTwo = ""
                                showCancelDialog = false
                                navController.navigate("login") {
                                    popUpTo("register") { inclusive = true }
                                }
                            }
                        ) {
                            Text("Aceptar", color = Primario)
                        }
                    },
                    dismissButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Fondo,
                            ),
                            onClick = {
                                showCancelDialog = false
                            },
                            border = BorderStroke(1.dp, Primario)
                        ) {
                            Text("Volver", color = Primario)
                        }
                    },
                    containerColor = Fondo,
                    shape = RoundedCornerShape(8.dp),
                )
            }

            if (showConfirmDialog) {
                AlertDialog(
                    onDismissRequest = { showConfirmDialog = false },
                    title = {
                        Text(
                            text = "Bienvenido",
                            style = MaterialTheme.typography.titleMedium,
                            color = Primario
                        )
                    },
                    text = {
                        Text(
                            text = "Sus datos se han registrado exitosamente.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextoSecundario
                        )
                    },
                    confirmButton = {
                        Box {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Fondo,
                                ),
                                border = BorderStroke(1.dp, Primario),
                                onClick = {
                                    var user = User(nombre,email,password)
                                    userViewModel.user = user

                                    nombre = ""
                                    email = ""
                                    password = ""
                                    passwordTwo = ""
                                    showConfirmDialog = false
                                    navController.navigate("login") {
                                        popUpTo("register") { inclusive = true }
                                    }
                                }
                            ) {
                                Text("Aceptar", color = Primario)
                            }
                        }
                    },
                    containerColor = Fondo,
                    shape = RoundedCornerShape(8.dp),
                )
            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(
        navController = rememberNavController(),
        userViewModel = TODO()
    )
}
