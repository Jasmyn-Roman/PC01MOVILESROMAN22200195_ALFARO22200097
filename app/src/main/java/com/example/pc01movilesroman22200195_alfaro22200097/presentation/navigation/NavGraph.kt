package com.example.pc01movilesroman22200195_alfaro22200097.presentation.navigation
import com.example.pc01movilesroman22200195_alfaro22200097.presentation.home.Pantalla3
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pc01movilesroman22200195_alfaro22200097.presentation.home.Pantalla0
import com.example.pc01movilesroman22200195_alfaro22200097.presentation.home.Pantalla1
import com.example.pc01movilesroman22200195_alfaro22200097.presentation.home.Pantalla2
import com.example.pc01movilesroman22200195_alfaro22200097.presentation.home.Pantalla4
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
            Pantalla1(navController = navController)
        }
        composable(Screen.PlanificadorPresupuesto.route) {
            Pantalla2(navController = navController)
        }
        composable(Screen.CatalogoDestinos.route) {
            Pantalla3(navController = navController)
        }
        composable(Screen.PermisoUbicacion.route) {
            Pantalla4(navController = navController)
        }
    }
}