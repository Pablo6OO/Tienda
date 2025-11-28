package com.example.tienda.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tienda.viewmodel.UsuarioViewModel

@Composable
fun RegistroScreen(viewModel: UsuarioViewModel, navController: NavController) {
    val estado by viewModel.estado.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = estado.nombre,
            onValueChange = { viewModel.onNombreChange(it) },
            label = { Text("Nombre") }
        )

        TextField(
            value = estado.correo,
            onValueChange = { viewModel.onCorreoChange(it) },
            label = { Text("Correo electrónico") }
        )

        TextField(
            value = estado.contrasenaHash,
            onValueChange = { viewModel.onContrasenaChange(it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        TextField(
            value = estado.telefono ?: "",
            onValueChange = { viewModel.onTelefonoChange(it) },
            label = { Text("Teléfono (opcional)") }
        )

        TextField(
            value = estado.direccion ?: "",
            onValueChange = { viewModel.onDireccionChange(it) },
            label = { Text("Dirección (opcional)") }
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = { viewModel.registrarUsuario() }) {
            Text("Registrar")
        }

        estado.errores?.correo?.let {
            Text(it, color = Color.Red)
        }
    }
}
