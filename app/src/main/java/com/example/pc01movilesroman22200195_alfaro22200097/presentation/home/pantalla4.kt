package com.example.pc01movilesroman22200195_alfaro22200097.presentation.home
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla4(navController: NavController) {

    var estadoPermiso by remember { mutableStateOf("pendiente") }
    // Estados: "pendiente", "concedido", "denegado"

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        estadoPermiso = if (isGranted) "concedido" else "denegado"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📍 Permiso de Ubicación", fontWeight = FontWeight.Bold) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                text = "Asistencia de Viaje",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Para brindarte asistencia personalizada durante tu viaje, necesitamos acceder a tu ubicación.",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Tarjeta de estado
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = when (estadoPermiso) {
                        "concedido" -> MaterialTheme.colorScheme.primaryContainer
                        "denegado" -> MaterialTheme.colorScheme.errorContainer
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = when (estadoPermiso) {
                            "concedido" -> "✅"
                            "denegado" -> "❌"
                            else -> "⏳"
                        },
                        fontSize = 48.sp
                    )
                    Text(
                        text = when (estadoPermiso) {
                            "concedido" -> "Permiso Concedido"
                            "denegado" -> "Permiso Denegado"
                            else -> "Permiso Pendiente"
                        },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = when (estadoPermiso) {
                            "concedido" -> "Tu ubicación está activa para asistencia de viaje"
                            "denegado" -> "No podremos brindarte asistencia basada en ubicación"
                            else -> "Aún no has solicitado el permiso de ubicación"
                        },
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Botón solicitar permiso
            if (estadoPermiso != "concedido") {
                Button(
                    onClick = {
                        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (estadoPermiso == "denegado")
                            "Solicitar permiso nuevamente"
                        else
                            "Solicitar permiso de ubicación",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}