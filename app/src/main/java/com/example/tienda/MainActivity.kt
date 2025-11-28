package com.example.tienda

import PerfilScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tienda.model.AppDatabase
import com.example.tienda.repository.UsuarioRepository
import com.example.tienda.ui.theme.TiendaTheme
import com.example.tienda.view.RegistroScreen
import com.example.tienda.view.homeScreen
import com.example.tienda.viewmodel.PerfilViewModel
import com.example.tienda.viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiendaTheme {
                val navController = rememberNavController()
                val perfilViewModel: PerfilViewModel = viewModel()

                val context = LocalContext.current
                val db = remember { AppDatabase.getDatabase(context) }
                val repository = remember { UsuarioRepository(db.UsuarioDao()) }
                val usuarioViewModel = remember { UsuarioViewModel(repository) }

                NavHost(
                    navController = navController,
                    startDestination = "HomeScreen"
                ) {
                    composable("HomeScreen") {
                        homeScreen(navController, perfilViewModel)
                    }
                    composable("Perfil") {
                        PerfilScreen(navController, perfilViewModel)
                    }
                    composable("RegistroScreen") {
                        RegistroScreen(usuarioViewModel, navController)
                    }
                }
            }
        }
    }
}
