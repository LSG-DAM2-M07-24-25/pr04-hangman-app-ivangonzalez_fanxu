package com.example.juegocolgado

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

@Composable
fun End(
    navController: NavController,
    result: String,
    word: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.White, Color.LightGray))) // Fondo con gradiente vertical
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (result == "win") "¡Has Ganado!" else "Has Perdido...",
                style = MaterialTheme.typography.headlineMedium,
                color = if (result == "win") Color(0xFF006400) else Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "La palabra era: $word",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Botón Volver al Menú
            Button(
                onClick = { navController.navigate(Routes.MenuScreen) },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.horizontalGradient(listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA)))) // Gradiente horizontal
                    .fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Volver al Menú",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Jugar de Nuevo
            Button(
                onClick = {
                    navController.popBackStack(Routes.GameScreen, inclusive = false) // Reinicia el juego
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.horizontalGradient(listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA)))) // Gradiente horizontal
                    .fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Jugar de Nuevo",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
        }
    }
}
