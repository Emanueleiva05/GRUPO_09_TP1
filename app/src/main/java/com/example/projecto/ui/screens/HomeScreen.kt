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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecto.R
import com.example.projecto.ui.theme.Fondo
import com.example.projecto.ui.theme.Primario


@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Primario,
                        Fondo,
                        Fondo,
                        Fondo
                    ),
                    startY = 0.5f * LocalDensity.current.density,
                    endY = LocalDensity.current.density * 1000f
                )
            )
            .padding(24.dp)
    )
    {
        Column(modifier = Modifier.fillMaxWidth()) {
            WelcomeMessage()
            PlatformSelector()
        }
    }
}

@Composable
fun WelcomeMessage() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Bienvenido juan_torres",
            color = Color.Black,
            style = TextStyle(
                fontSize = 30.sp,
            ),
            modifier = Modifier.align(Alignment.Center),

        )
    }
}

@Composable
fun PlatformSelector() {
    var selectedPlatform by remember { mutableStateOf("android") }

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
                    selectedPlatform = "android"
                }
            }
        )
        PlatformItem(
            image = R.drawable.ic_android,
            text = "iOS",
            selected = selectedPlatform == "iOS",
            onClick = {
                if (selectedPlatform != "iOS") {
                    selectedPlatform = "iOS"
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
                color = if (selected) Color(0x3D000000) else Color.Transparent,
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
        Text(text = text)
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}