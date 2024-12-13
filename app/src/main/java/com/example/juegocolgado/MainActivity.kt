//gradel 8.9
//SDK version 35
package com.example.juegocolgado

import Game
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationController = rememberNavController()
            NavHost(
                navController = navigationController,
                startDestination = Routes.StartScreen
            ) {
                composable(Routes.StartScreen) { Start(navigationController) }
                composable(Routes.MenuScreen) { Menu(navigationController) }
                composable(
                    route = Routes.GameScreen,
                    arguments = listOf(navArgument("difficulty") { type = NavType.StringType })
                ) { backStackEntry ->
                    // Extraemos el argumento 'difficulty' de backStackEntry
                    val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "Facil"
                    Game(targetWord = "", navController = navigationController, difficulty = difficulty)
                }
                composable(
                    route = "${Routes.EndScreen}/{result}/{word}",
                    arguments = listOf(
                        navArgument("result") { type = NavType.StringType },
                        navArgument("word") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val result = backStackEntry.arguments?.getString("result") ?: "lose"
                    val word = backStackEntry.arguments?.getString("word") ?: ""
                    End(navController = navigationController, result = result, word = word)
                }
            }


        }
    }
}