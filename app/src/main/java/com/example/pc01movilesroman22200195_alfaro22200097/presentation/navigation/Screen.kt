package com.example.pc01movilesroman22200195_alfaro22200097.presentation.navigation

sealed class Screen(val route: String) {
    object MenuPrincipal : Screen("menu_principal")
    object CalculadoraEquipaje : Screen("calculadora_equipaje")
    object PlanificadorPresupuesto : Screen("planificador_presupuesto")
    object CatalogoDestinos : Screen("catalogo_destinos")
    object PermisoUbicacion : Screen("permiso_ubicacion")
}