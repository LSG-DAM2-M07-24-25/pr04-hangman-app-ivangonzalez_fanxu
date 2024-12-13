//gradel 8.9
//SDK version 35
package com.example.juegocolgado

import Game
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.juegocolgado.ui.theme.JuegoColgadoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JuegoColgadoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
            val navigationController = rememberNavController()
            var dificultad by remember { mutableStateOf("") }
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
                composable(Routes.EndScreen) { End(navigationController) }
            }


        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JuegoColgadoTheme {
        Greeting("Android")
    }
}
