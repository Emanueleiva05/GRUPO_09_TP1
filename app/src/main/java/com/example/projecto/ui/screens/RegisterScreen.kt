package com.example.projecto.ui.screens
//Diseño y logica para que nuevo usuario cree una cuenta.

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import com.example.projecto.ui.theme.Primario
import com.example.projecto.ui.theme.Texto
import com.example.projecto.ui.theme.TextoSecundario
import com.example.projecto.ui.theme.Fondo
import com.example.projecto.ui.theme.Secundario


//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff


@Composable
fun RegisterScreen(navController: NavController) {
    //Estado para guardar lo que escribe el usuario.
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf( "") }
    var password by remember { mutableStateOf("") }
    var passwordTwo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            color = Primario,
            text = "Bienvenido a nuestra App.",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(bottom = 24.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Nombre",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp,bottom = 4.dp),
            color = Texto,
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = MaterialTheme.shapes.small
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Correo Electronico",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp,bottom = 2.dp),
            color = Texto,
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = MaterialTheme.shapes.small
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Contraseña",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp,bottom = 2.dp),
            color = Texto,
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = MaterialTheme.shapes.small
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Repetir Contraseña",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp,bottom = 2.dp),
            color = Texto,
        )

        OutlinedTextField(
            value = passwordTwo,
            onValueChange = { passwordTwo = it },
            label = { Text("Repetir contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = MaterialTheme.shapes.small
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Button(
                onClick = {
                    //Aca va la logica
                },
                modifier = Modifier
                    .weight(1f),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primario
                ),
            ){
                Text("Cancelar", color = Texto)
            }
            Button(
                onClick = {
                    //Aca va la logica
                    navController.navigate("login")
                },
                modifier = Modifier
                    .weight(1f),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primario
                ),

            ){
                Text("Registrarme", color = Texto)

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(navController = rememberNavController())
}
