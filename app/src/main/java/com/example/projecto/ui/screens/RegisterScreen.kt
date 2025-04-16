package com.example.projecto.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import com.example.projecto.ui.theme.Primario
import com.example.projecto.ui.theme.Texto
import com.example.projecto.ui.theme.TextoSecundario
import com.example.projecto.ui.theme.Fondo
import com.example.projecto.ui.theme.Secundario



//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff


@Composable
fun RegisterScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordTwo by remember { mutableStateOf("") }

    var nombreError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    var showCancelDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors=listOf(
                            Fondo,
                            Fondo,
                            Primario
                        ),
                        startY=0.5f * LocalDensity.current.density,
                        endY= LocalDensity.current.density * 1000f
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                color = Primario,
                text = "Bienvenido a nuestra App.",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(8.dp)
                    .shadow(elevation = 16.dp, shape = RoundedCornerShape(16.dp))
                    .background(Fondo, shape = RoundedCornerShape(16.dp))
            ) {
                Column {
                    Spacer(modifier = Modifier.height(64.dp))
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = passwordTwo,
                        onValueChange = { passwordTwo = it },
                        label = { Text("Repetir contraseña", color = Texto) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        shape = MaterialTheme.shapes.small
                    )
                    Spacer(modifier = Modifier.height(72.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                showCancelDialog = true
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding( start = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Primario),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Fondo,
                            ),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 16.dp)
                        ) {
                            Text("Cancelar", color = Texto)
                        }
                        Button(
                            onClick = {
                                val isEmailValid = email.isNotBlank()
                                val isPasswordValid = password.length >= 6
                                val isNombreValid = nombre.isNotBlank()

                                nombreError = !isNombreValid
                                emailError = !isEmailValid
                                passwordError = !isPasswordValid

                                if (isNombreValid && isEmailValid && isPasswordValid) {
                                    navController.navigate("login") {
                                        popUpTo("register") { inclusive = true }
                                    }
                                }
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(end = 8.dp),
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
                    }
                }

            }
            if (showCancelDialog) {
                AlertDialog(
                    onDismissRequest = { showCancelDialog = false },
                    title = { Text("¿Cancelar registro?") },
                    text = { Text("Se perderán todos los datos ingresados.") },
                    confirmButton = {
                        Button(
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
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showCancelDialog = false
                            }
                        ) {
                            Text("Volver")
                        }
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(navController = rememberNavController())
}
