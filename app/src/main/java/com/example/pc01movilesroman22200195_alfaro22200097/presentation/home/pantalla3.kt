
package com.example.pc01movilesroman22200195_alfaro22200097.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pc01movilesroman22200195_alfaro22200097.data.model.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla3(navController: NavController) {

    val destinos = listOf(
        Destination(
            pais = "Perú",
            ciudad = "Cusco",
            costoPromedio = 250.0,
            imagenUrl = "https://picsum.photos/id/1011/400/300"
        ),
        Destination(
            pais = "Francia",
            ciudad = "París",
            costoPromedio = 800.0,
            imagenUrl = "https://picsum.photos/id/1018/400/300"
        ),
        Destination(
            pais = "Japón",
            ciudad = "Tokio",
            costoPromedio = 950.0,
            imagenUrl = "https://picsum.photos/id/1015/400/300"
        ),
        Destination(
            pais = "Italia",
            ciudad = "Roma",
            costoPromedio = 700.0,
            imagenUrl = "https://picsum.photos/id/1016/400/300"
        ),
        Destination(
            pais = "Brasil",
            ciudad = "Río de Janeiro",
            costoPromedio = 450.0,
            imagenUrl = "https://picsum.photos/id/1019/400/300"
        )
    )
    val totalDestinos = destinos.size
    val sumaTotal = destinos.sumOf { it.costoPromedio }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🌍 Catálogo de Destinos", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("←", fontSize = 20.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(destinos) { destino ->
                DestinoCard(destino = destino)
            }

            // Resumen al final
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "📊 Resumen",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Total de destinos: $totalDestinos",
                            fontSize = 15.sp
                        )
                        Text(
                            text = "Suma de costos: S/ ${"%.2f".format(sumaTotal)}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DestinoCard(destino: Destination) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            AsyncImage(
                model = destino.imagenUrl,
                contentDescription = destino.ciudad,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = destino.pais,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = destino.ciudad,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "💵 S/ ${"%.2f".format(destino.costoPromedio)} / día",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}