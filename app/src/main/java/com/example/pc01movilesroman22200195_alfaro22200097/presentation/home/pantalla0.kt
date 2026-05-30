package com.example.pc01movilesroman22200195_alfaro22200097.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pc01movilesroman22200195_alfaro22200097.data.model.MenuItem
import com.example.pc01movilesroman22200195_alfaro22200097.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla0(navController: NavController) {

    val menuItems = listOf(
        MenuItem(
            title = "🧳 Calculadora de Equipaje",
            description = "Verifica si tu maleta cumple el límite de peso",
            route = Screen.CalculadoraEquipaje.route,
            icon = "🧳"
        ),
        MenuItem(
            title = "💰 Planificador de Presupuesto",
            description = "Calcula el presupuesto total de tu viaje",
            route = Screen.PlanificadorPresupuesto.route,
            icon = "💰"
        ),
        MenuItem(
            title = "🌍 Catálogo de Destinos",
            description = "Explora destinos turísticos disponibles",
            route = Screen.CatalogoDestinos.route,
            icon = "🌍"
        ),
        MenuItem(
            title = "📍 Permiso de Ubicación",
            description = "Activa tu ubicación para asistencia de viaje",
            route = Screen.PermisoUbicacion.route,
            icon = "📍"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "✈️ Travel Companion",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "¿A dónde vamos hoy?",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            menuItems.forEach { item ->
                MenuItemCard(item = item, onClick = {
                    navController.navigate(item.route)
                })
            }
        }
    }
}

@Composable
fun MenuItemCard(item: MenuItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = item.description,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ir →")
            }
        }
    }
}