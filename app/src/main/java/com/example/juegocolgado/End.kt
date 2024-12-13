package com.example.juegocolgado

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun End(
    navController: NavController,
    result: String, // "win" o "lose"
    word: String // Palabra objetivo
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (result == "win") "¡Ganaste!" else "Perdiste...",
                style = MaterialTheme.typography.headlineMedium,
                color = if (result == "win") Color.Green else Color.Red
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "La palabra era: $word",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate(Routes.MenuScreen) },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.Black),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Volver al Menú")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.popBackStack(Routes.GameScreen, inclusive = false)
                }, // Reinicia el juego directamente
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.Black),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Jugar de Nuevo")
            }
        }
    }
}


