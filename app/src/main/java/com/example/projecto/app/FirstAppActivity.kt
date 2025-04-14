package com.example.projecto.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projecto.R
import android.content.Intent

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //Esta funcion se llamara cada vez que se lance la pantalla
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_app) //La visual de pantalla
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val intent = Intent(this, InicioSesionActivity::class.java) //El intent() lo que hace es decir quiero hacer esto con esto otro, en este caso desde esta actividad (this) abrir iniciosesionactivity
            startActivity(intent) //Se lanza con startactivity
        }
    }
}