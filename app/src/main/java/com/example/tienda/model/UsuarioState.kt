package com.example.tienda.model

data class UsuarioState(
    val nombre: String = "",
    val correo: String = "",
    val clave: String = "",
    val telefono: String = "",
    val direccion: String = "",
    val errores: ErroresUsuario = ErroresUsuario()
)