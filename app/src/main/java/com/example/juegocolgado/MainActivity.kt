//gradel 8.9
//SDK version 35
package com.example.juegocolgado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            NavHost(
                navController = navigationController,
                startDestination = Routes.StartScreen.route
            ) {
                composable(Routes.StartScreen.route) { Start(navigationController) }
                composable(Routes.MenuScreen.route) { Menu(navigationController) }
                composable(Routes.GameScreen.route) { Game(navigationController) }
                composable(Routes.EndScreen.route) { End(navigationController) }
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