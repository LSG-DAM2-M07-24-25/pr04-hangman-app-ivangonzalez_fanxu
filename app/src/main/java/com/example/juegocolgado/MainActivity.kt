//gradel 8.9
//SDK version 35
package com.example.juegocolgado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
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

@Composable
fun Start(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Green)) {
        Text(
            text = "Start Screen",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable
                { navController.navigate(Routes.MenuScreen.route) })
    }
}

@Composable
fun Menu(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red)) {
        Text(
            text = "Menu Screen",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable
                { navController.navigate(Routes.GameScreen.route) })
    }
}

@Composable
fun Game(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Blue)) {
        Text(
            text = "Game Screen",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable
                { navController.navigate(Routes.EndScreen.route) })
    }
}

@Composable
fun End(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Magenta)) {
        Text(
            text = "Start Screen",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable
                { navController.navigate(Routes.StartScreen.route) })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JuegoColgadoTheme {
        Greeting("Android")
    }
}