package com.ejemplo.coinswapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ejemplo.coinswapi.ui.Navigation.ScreenRoutes
import com.ejemplo.coinswapi.ui.Screens.CoinsScreen
import com.ejemplo.coinswapi.ui.Screens.RegistroCoinScreen
import com.ejemplo.coinswapi.ui.theme.CoinsWApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinsWApiTheme {
                SegundoParcialFrankelynApp()
            }
        }
    }
}


@Composable
fun SegundoParcialFrankelynApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val navHostController = rememberNavController()

        NavHost(
            navController = navHostController,
            startDestination = ScreenRoutes.ConsultaCoinsScreen.ruta
        ) {
            composable(ScreenRoutes.ConsultaCoinsScreen.ruta) {
                CoinsScreen(
                    navHostController = navHostController
                )
            }

            composable(ScreenRoutes.PostCoinScreen.ruta){
                RegistroCoinScreen(
                    navHostController = navHostController
                )
            }

        }
    }
}