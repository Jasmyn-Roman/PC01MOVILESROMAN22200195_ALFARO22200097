package com.example.pc01movilesroman22200195_alfaro22200097.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla2(navController: NavController) {

    var dias by remember { mutableStateOf("") }
    var presupuestoDiario by remember { mutableStateOf("") }
    var tipoAlojamiento by remember { mutableStateOf("Estándar") }
    var resultado by remember { mutableStateOf("") }
    var errorDias by remember { mutableStateOf("") }
    var errorPresupuesto by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val tiposAlojamiento = listOf(
        Triple("Económico", 0.8, "Alojamiento básico y funcional"),
        Triple("Estándar", 1.0, "Alojamiento con servicios completos"),
        Triple("Premium", 1.5, "Alojamiento de lujo y confort")
    )

    fun calcular() {
        var hayError = false

        if (dias.isBlank()) {
            errorDias = "La cantidad de días es obligatoria"
            hayError = true
        } else if (dias.toIntOrNull() == null) {
            errorDias = "Ingresa un valor numérico"
            hayError = true
        } else if (dias.toInt() <= 0) {
            errorDias = "Los días deben ser mayor a cero"
            hayError = true
        } else {
            errorDias = ""
        }

        if (presupuestoDiario.isBlank()) {
            errorPresupuesto = "El presupuesto diario es obligatorio"
            hayError = true
        } else if (presupuestoDiario.toDoubleOrNull() == null) {
            errorPresupuesto = "Ingresa un valor numérico"
            hayError = true
        } else if (presupuestoDiario.toDouble() <= 0) {
            errorPresupuesto = "El presupuesto debe ser mayor a cero"
            hayError = true
        } else {
            errorPresupuesto = ""
        }

        if (hayError) {
            resultado = ""
            return
        }

        val d = dias.toInt()
        val p = presupuestoDiario.toDouble()
        val factor = tiposAlojamiento.first { it.first == tipoAlojamiento }.second
        val total = d * p * factor

        val mensaje = when (tipoAlojamiento) {
            "Económico" -> "Viaje económico de $d días con presupuesto ajustado"
            "Estándar" -> "Viaje estándar de $d días con servicios completos"
            "Premium" -> "Viaje premium de $d días con experiencia de lujo"
            else -> ""
        }

        resultado = "💰 Total: S/ ${"%.2f".format(total)}\n📝 $mensaje"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("💰 Planificador de Presupuesto", fontWeight = FontWeight.Bold) },
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
                text = "Planifica el presupuesto de tu viaje",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            // Campo días
            OutlinedTextField(
                value = dias,
                onValueChange = {
                    dias = it
                    errorDias = ""
                },
                label = { Text("Cantidad de días") },
                isError = errorDias.isNotEmpty(),
                supportingText = {
                    if (errorDias.isNotEmpty()) {
                        Text(errorDias, color = MaterialTheme.colorScheme.error)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campo presupuesto diario
            OutlinedTextField(
                value = presupuestoDiario,
                onValueChange = {
                    presupuestoDiario = it
                    errorPresupuesto = ""
                },
                label = { Text("Presupuesto diario (S/)") },
                isError = errorPresupuesto.isNotEmpty(),
                supportingText = {
                    if (errorPresupuesto.isNotEmpty()) {
                        Text(errorPresupuesto, color = MaterialTheme.colorScheme.error)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Dropdown tipo alojamiento
            Text(
                text = "Tipo de alojamiento:",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = tipoAlojamiento,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Selecciona alojamiento") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    tiposAlojamiento.forEach { (nombre, factor, descripcion) ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(nombre, fontWeight = FontWeight.Bold)
                                    Text("Factor: $factor — $descripcion", fontSize = 12.sp)
                                }
                            },
                            onClick = {
                                tipoAlojamiento = nombre
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Botón calcular
            Button(
                onClick = { calcular() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular presupuesto", fontSize = 16.sp)
            }

            // Resultado
            if (resultado.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = resultado,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}