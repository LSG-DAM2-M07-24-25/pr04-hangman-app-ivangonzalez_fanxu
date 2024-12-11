package com.fanxu.hangmanmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fanxu.hangmanmvvm.ui.theme.HangManMVVMTheme
import com.fanxu.hangmanmvvm.view.Carga.CargaScreen
import com.fanxu.hangmanmvvm.view.Carga.CargaViewModel
import com.fanxu.hangmanmvvm.view.Final.FinalScreen
import com.fanxu.hangmanmvvm.view.Final.FinalViewModel
import com.fanxu.hangmanmvvm.view.Juego.JuegoScreen
import com.fanxu.hangmanmvvm.view.Juego.JuegoViewModel
import com.fanxu.hangmanmvvm.view.Menu.MenuScreen
import com.fanxu.hangmanmvvm.view.Menu.MenuViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HangManMVVMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.StartScreen.route
                    ) {
                        composable(Routes.StartScreen.route) {
                            CargaScreen(viewModel = CargaViewModel(), navController = navController)
                        }
                        composable(Routes.Menu.route) {
                            MenuScreen(viewModel = MenuViewModel(), navController = navController)
                        }
                        composable(Routes.Juego.route) {
                            JuegoScreen(viewModel = JuegoViewModel(), navController = navController)
                        }
                        composable(Routes.Final.route) {
                            FinalScreen(viewModel = FinalViewModel(), navController = navController)
                        }
                    }
                }
            }
        }
    }
}
