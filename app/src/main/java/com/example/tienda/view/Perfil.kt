package com.example.tienda.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.example.tienda.viewmodel.PerfilViewModel
import com.example.tienda.viewmodel.UsuarioViewModel2
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(navController : NavController, usuarioViewModel: UsuarioViewModel2, viewModel: PerfilViewModel) {
    val context = LocalContext.current
    val imagenUri by viewModel.imagenUri.collectAsState()
    val usuario by usuarioViewModel.usuarioActual.collectAsState()
    val descripcion by viewModel.descripcion.collectAsState()
    var descripcionTemp by remember { mutableStateOf(descripcion) }

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.setImage(uri)
    }

    var cameraUri by remember { mutableStateOf<Uri?>(null) }
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) viewModel.setImage(cameraUri)
    }

    fun createImageUri(context: Context): Uri {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Perfil de Usuario") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImagenPerfil(imagenUri)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { pickImageLauncher.launch("image/*") }) {
                Text("Elegir de galería")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) -> {
                        val uri = createImageUri(context)
                        cameraUri = uri
                        takePictureLauncher.launch(uri)
                    }
                    else -> {
                        // Aquí podrías manejar permiso no concedido
                    }
                }
            }) {
                Text("Tomar foto con cámara")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Campos editables
            Text("Nombre: ${usuario?.nombre ?: "No registrado"}")
            Spacer(modifier = Modifier.height(12.dp))
            Text("Correo: ${usuario?.correo ?: "No registrado"}")
            Spacer(modifier = Modifier.height(12.dp))
            Text("Teléfono: ${usuario?.telefono ?: "No registrado"}")

            Spacer(modifier = Modifier.height(12.dp))

            Text("Descripción", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            BasicTextField(
                value = descripcionTemp,
                onValueChange = { descripcionTemp = it },
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFFF1F1F1))
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                viewModel.setDescripcion(descripcionTemp)
            }) {
                Text("Guardar cambios (temporal)")
            }
        }
    }
}