package com.example.tienda.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tienda.model.Producto
import com.example.tienda.viewmodel.PerfilViewModel
import java.util.Objects.toString


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(navController: NavController, viewModel: PerfilViewModel) {

    val imagenUri by viewModel.imagenUri.collectAsState()

    val productos = listOf(
        Producto(1, "Polera Básica","Polera básica de color negro", "Polera", "24", "Negro", 9.990, 7.990, true, true, 15, "polera", System.currentTimeMillis()),
        Producto(2, "Pantalón Buzo","Pantalón negro que es un buzo", "Pantalon", "32", "Negro", 14.990, 10.990, true, true, 22, "pantalon", System.currentTimeMillis()),
        Producto(3, "Zapatillas","Zapatillas de mujer de buena calidad", "Zapatillas", "22", "Gris", 24.990, 19.990, true, true, 35, "zapatos", System.currentTimeMillis()),
        Producto(4, "Chaqueta","Chaqueta negra para hombre", "Chaqueta", "36", "Negro", 19.990, 16.990, true, true, 12, "chaqueta", System.currentTimeMillis())
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Productos en tienda") },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .clickable {
                                navController.navigate("Perfil")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        ImagenPerfil(imagenUri)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Productos a la venta️",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productos) { producto ->
                    ProductoCard(producto)
                }
            }
            Text(
                text = "Productos ️en arriendo",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productos) { producto ->
                    ArriendoCard(producto)
                }
            }
        }
    }
}

@Composable
fun ArriendoCard(producto: Producto) {

    val context = androidx.compose.ui.platform.LocalContext.current

    val imageResId = context.resources.getIdentifier(producto.imagen, "drawable", context.packageName)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )

            Text(
                text = producto.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                text = "$" + toString(producto.precioArriendo) + "0",
                fontSize = 14.sp
            )

            Button(onClick = { /* agregar al carrito, etc. */ }) {
                Text("Agregar")
            }
        }
    }
}

@Composable
fun ProductoCard(producto: Producto) {

    val context = androidx.compose.ui.platform.LocalContext.current

    val imageResId = context.resources.getIdentifier(producto.imagen, "drawable", context.packageName)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )

            Text(
                text = producto.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                text = "$" + toString(producto.precioVenta) + "0",
                fontSize = 14.sp
            )

            Button(onClick = { /* agregar al carrito, etc. */ }) {
                Text("Agregar")
            }
        }
    }
}

