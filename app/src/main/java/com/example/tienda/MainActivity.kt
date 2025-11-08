package com.example.tienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tienda.ui.theme.TiendaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //DENTRO DE AQUÍ DEBEN INSTANCIAR LA FUNCIÓN QUE HARÁ EL FLUJO PRINCIPAL
      
            //TiendaTheme(){
                
                //Surface(
                    //modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colorScheme.background
                //) {
                    
                    //homeScreen()
            //ando en clases de base datos 2 hoy sabado no pude hacer mucho pero almenos logre que se viera homeScreen (la polera) lo demas no me alcanzo el tiempo para hacerlo funcionar se los dejo aqui como comentario pero deberia cargar
        }
    }
}

