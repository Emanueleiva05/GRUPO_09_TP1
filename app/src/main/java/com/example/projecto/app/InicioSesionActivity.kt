package com.example.projecto.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projecto.R
import android.content.Intent
import androidx.appcompat.widget.AppCompatEditText
import android.widget.Toast
import com.example.projecto.app.data.UserData

class InicioSesionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio_sesion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnlog = findViewById<AppCompatButton>(R.id.btnlog)
        val name = findViewById<AppCompatEditText>(R.id.etUser)
        val pass = findViewById<AppCompatEditText>(R.id.etPassword)

        val users = UserData.users

        btnlog.setOnClickListener {
            val nameText = name.text.toString()
            val passText = pass.text.toString()
            var encontrado: Boolean = false

            for (user in users){
                if(user.usuario == nameText && user.password==passText){
                    encontrado = true
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    break
                }
            }

            if(!encontrado){
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}