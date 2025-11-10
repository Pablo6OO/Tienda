package com.example.tienda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tienda.data.repository.UsuarioRepository
import com.example.tienda.model.ErroresUsuario
import com.example.tienda.model.Usuario
import com.example.tienda.model.UsuarioState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest

data class UsuarioState(
    val nombre: String = "",
    val correo: String = "",
    val clave: String = "",
    val telefono: String = "",
    val direccion: String = ""
)

class UsuarioViewModel2(private val repository: UsuarioRepository) : ViewModel() {

    private val _estado = MutableStateFlow(UsuarioState())
    val estado: StateFlow<UsuarioState> = _estado.asStateFlow()

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual = _usuarioActual.asStateFlow()

    fun onNombreChange(nuevo: String) = updateState { it.copy(nombre = nuevo) }
    fun onCorreoChange(nuevo: String) = updateState { it.copy(correo = nuevo) }
    fun onClaveChange(nuevo: String) = updateState { it.copy(clave = nuevo) }
    fun onTelefonoChange(nuevo: String) = updateState { it.copy(telefono = nuevo) }
    fun onDireccionChange(nuevo: String) = updateState { it.copy(direccion = nuevo) }

    private fun updateState(block: (UsuarioState) -> UsuarioState) {
        _estado.value = block(_estado.value)
    }

    fun registrarUsuario() {
        val datos = _estado.value
        if (datos.nombre.isBlank() || datos.correo.isBlank() || datos.clave.isBlank()) return

        viewModelScope.launch {
            val usuario = Usuario(
                id = 1,
                nombre = datos.nombre,
                correo = datos.correo,
                contrasenaHash = datos.clave,
                telefono = datos.telefono,
                direccion = datos.direccion
            )
            repository.insert(usuario)
            _usuarioActual.value = usuario
        }
    }

    fun cargarUsuario(id: Int) {
        // Temporal: no carga desde DB, solo usa el actual
        _usuarioActual.value = _usuarioActual.value
    }

    fun actualizarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            repository.update(usuario)
            _usuarioActual.value = usuario
        }
    }
}
