package com.example.projecto.app.data

// Clase que representa un usuario
data class User(val usuario: String, val email: String, val password: String)

// Objeto singleton que guarda la lista de usuarios
object UserData {
    val users = mutableListOf<User>()
}

//Para agregar objetos a la lista se usa en cualquier pantalla
//UserData.users.add(User(parametros que estan especificados arriba)) Aca primero ingresaremos a la lista de usuarios "userdata" y agragamos un User a la variable users

//Despues para poder obtener datos uso
//val lista = UserData.users