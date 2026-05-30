package com.example.pc01movilesroman22200195_alfaro22200097

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.pc01movilesroman22200195_alfaro22200097.presentation.navigation.NavGraph
import com.example.pc01movilesroman22200195_alfaro22200097.ui.theme.PC01MOVILESROMAN22200195_ALFARO22200097Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PC01MOVILESROMAN22200195_ALFARO22200097Theme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}