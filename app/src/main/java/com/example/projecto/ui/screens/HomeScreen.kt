//Home - Luego de iniciar sesion el usuario viene aca.

package com.example.projecto.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecto.R
import com.example.projecto.ui.theme.Fondo
import com.example.projecto.ui.theme.Primario
import com.example.projecto.data.UserViewModel



@Composable
fun HomeScreen(navController: NavController, userViewModel: UserViewModel) {
    var selectedPlatform by remember { mutableStateOf<String?>(null) }
    val selectedPreferences = remember { mutableStateMapOf<String, Boolean>() }
    var otraTexto by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(top = 100.dp, start = 24.dp, end = 24.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Seleccione una plataforma",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                PlatformSelector(
                    selectedPlatform = selectedPlatform,
                    onPlatformSelected = { selectedPlatform = it }
                )
                Spacer(Modifier.height(30.dp))
                Text(
                    text = "Seleccione sus preferencias",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                PereferenceSelector(
                    selectedPreferences = selectedPreferences,
                    otraTexto = otraTexto,
                    onOtraTextoChanged = { otraTexto = it }
                )
                SubmitBtn(
                    selectedPlatform = selectedPlatform,
                    selectedPreferences = selectedPreferences,
                    otraTexto = otraTexto
                )
            }
        }
    }
    WelcomeMessage(userViewModel)//Tiene que estar aca para que se superponga a lo otro
}


@Composable
fun WelcomeMessage(userViewModel: UserViewModel) {
    val user = userViewModel.user
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Primario, Fondo, Fondo),
                    startX = 0f,
                    endX = LocalDensity.current.density * 1000f
                )
            )
            .padding(24.dp)
    ) {
        Text(
            text = "Bienvenido ${user?.nombre}",
            color = Color.Black,
            style = TextStyle(fontSize = 30.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun PlatformSelector(
    selectedPlatform: String?,
    onPlatformSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PlatformItem(
            image = R.drawable.ic_android,
            text = "Android",
            selected = selectedPlatform == "android",
            onClick = {
                if (selectedPlatform != "android") {
                    onPlatformSelected("android")
                }
            }
        )
        PlatformItem(
            image = R.drawable.ic_apple,
            text = "iOS",
            selected = selectedPlatform == "iOS",
            onClick = {
                if (selectedPlatform != "iOS") {
                    onPlatformSelected("iOS")
                }
            }
        )
    }
}

@Composable
fun PlatformItem(
    image: Int,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
            .background(
                color = if (selected) Color(0x3DFFFFFF) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = text,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, color = Color.White)
    }
}

@Composable
fun PereferenceSelector(
    selectedPreferences: MutableMap<String, Boolean>,
    otraTexto: String,
    onOtraTextoChanged: (String) -> Unit
) {
    // Creo una lista de las preferencias en vez de poner una por una
    val preferences = listOf("Programación", "Redes", "Seguridad", "Hardware")

    var otraIsChecked by remember { mutableStateOf(false) }

    preferences.forEach { pref ->
        val isChecked = selectedPreferences[pref] == true

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    // si hace clic en cualquier lugar de la row cambio el estado de la pref
                    selectedPreferences[pref] = !isChecked
                }
        ) {
            Checkbox(
                checked = isChecked,
                //evito que con un clic cambie de estado dos veces si toca la checkbox
                onCheckedChange = null
            )
            Text(
                text = pref,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                otraIsChecked = !otraIsChecked
                selectedPreferences["Otra"] = otraIsChecked
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = otraIsChecked,
                onCheckedChange = null
            )
            Text(
                text = "Otra",
                modifier = Modifier.padding(8.dp),
                color = Color.White,
            )
        }
        Row {
            if (otraIsChecked) {
                TextField(
                    value = otraTexto,
                    onValueChange = onOtraTextoChanged,
                    placeholder = { Text("Especifica tu preferencia") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(56.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White
                    )

                )
            }
        }
    }
}

@Composable
fun SubmitBtn(
    selectedPlatform: String?,
    selectedPreferences: Map<String, Boolean>,
    otraTexto: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = {
                val selectedPrefs = selectedPreferences.filter { it.value }.keys.toList()

                println("Formulario enviado")
                println("Plataforma seleccionada: $selectedPlatform")
                println("Preferencias seleccionadas: $selectedPrefs")

                if ("Otra" in selectedPrefs && otraTexto.isNotBlank()) {
                    println("Texto ingresado en 'Otra': $otraTexto")
                }
            },
            modifier = Modifier
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Enviar")
        }

    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), userViewModel = TODO())
}