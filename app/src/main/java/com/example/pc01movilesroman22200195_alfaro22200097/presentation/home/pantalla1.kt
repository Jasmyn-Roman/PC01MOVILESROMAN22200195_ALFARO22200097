package com.example.pc01movilesroman22200195_alfaro22200097.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla1(navController: NavController) {

    var pesoMaleta by remember { mutableStateOf("") }
    var tipoVuelo by remember { mutableStateOf("Nacional") }
    var resultado by remember { mutableStateOf("") }
    var errorPeso by remember { mutableStateOf("") }
    var excede by remember { mutableStateOf(false) }

    val tiposVuelo = listOf("Nacional", "Internacional")

    fun calcular() {
        // Validación campo vacío
        if (pesoMaleta.isBlank()) {
            errorPeso = "El peso es obligatorio"
            resultado = ""
            return
        }

        // Validación numérico
        val peso = pesoMaleta.toDoubleOrNull()
        if (peso == null) {
            errorPeso = "Ingresa un valor numérico"
            resultado = ""
            return
        }

        // Validación mayor a cero
        if (peso <= 0) {
            errorPeso = "El peso debe ser mayor a cero"
            resultado = ""
            return
        }

        errorPeso = ""

        // Límite según tipo de vuelo
        val limite = if (tipoVuelo == "Nacional") 23.0 else 32.0

        if (peso <= limite) {
            excede = false
            resultado = "✅ Cumple el límite permitido ($limite kg)"
        } else {
            excede = true
            val kgExcedidos = peso - limite
            resultado = "❌ Excede el límite en ${"%.2f".format(kgExcedidos)} kg"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🧳 Calculadora de Equipaje", fontWeight = FontWeight.Bold) },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Ingresa los datos de tu equipaje",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            // Campo peso
            OutlinedTextField(
                value = pesoMaleta,
                onValueChange = {
                    pesoMaleta = it
                    errorPeso = ""
                },
                label = { Text("Peso de la maleta (kg)") },
                isError = errorPeso.isNotEmpty(),
                supportingText = {
                    if (errorPeso.isNotEmpty()) {
                        Text(errorPeso, color = MaterialTheme.colorScheme.error)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Selector tipo de vuelo
            Text(
                text = "Tipo de vuelo:",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )

            tiposVuelo.forEach { tipo ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = tipoVuelo == tipo,
                        onClick = { tipoVuelo = tipo }
                    )
                    Text(
                        text = if (tipo == "Nacional") "Nacional (máx. 23 kg)"
                        else "Internacional (máx. 32 kg)",
                        fontSize = 15.sp
                    )
                }
            }

            // Botón calcular
            Button(
                onClick = { calcular() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular", fontSize = 16.sp)
            }

            // Resultado
            if (resultado.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (excede)
                            MaterialTheme.colorScheme.errorContainer
                        else
                            Color(0xFF90EE90)
                    )
                ) {
                    Text(
                        text = resultado,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}