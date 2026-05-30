package com.example.pc01movilesroman22200195_alfaro22200097.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pc01movilesroman22200195_alfaro22200097.presentation.home.Pantalla0

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MenuPrincipal.route
    ) {
        composable(Screen.MenuPrincipal.route) {
            Pantalla0(navController = navController)
        }
        composable(Screen.CalculadoraEquipaje.route) {
        }
        composable(Screen.PlanificadorPresupuesto.route) { }
        composable(Screen.CatalogoDestinos.route) { }
        composable(Screen.PermisoUbicacion.route) { }
    }
}