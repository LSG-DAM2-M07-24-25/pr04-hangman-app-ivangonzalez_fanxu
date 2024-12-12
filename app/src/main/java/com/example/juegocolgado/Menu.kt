package com.example.juegocolgado

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Menu(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.White, Color.LightGray)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box (
                modifier = Modifier
                    .size(300.dp)
            ) {
                Image(
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(
                        id = R.drawable.hangnn
                    ),
                    contentDescription = "Game icon",
                    modifier = Modifier.matchParentSize()
                )
            }
            Box {
                DropDownDifficulty(
                    modifier = Modifier
                        .padding(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray)
                )
            }
            Box(
                modifier = Modifier
                    .clickable { navController.navigate(Routes.GameScreen.route) }
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFDD6AE5),
                                Color(0xFFAA00AA)

                            )
                        )
                    )
                    .padding(horizontal = 72.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Play",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(10.dp)
            )
            Box(
                modifier = Modifier
                    .clickable {  }
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFDD6AE5),
                                Color(0xFFAA00AA)

                            )
                        )
                    )
                    .padding(horizontal = 72.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Help",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownDifficulty(modifier: Modifier = Modifier) {
    var selectedText: String by remember { mutableStateOf("") }
    var expanded: Boolean by remember { mutableStateOf(false) }
    val difficulties = listOf("Easy", "Medium", "Hard")

    Column(
        modifier = modifier
            .padding(12.dp) // Compactar el padding
    ) {
        // Campo de texto
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
                .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp)), // Fondo suave
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
            placeholder = {
                Text("Select Difficulty", color = Color(0xFFAA00AA)) // Placeholder bonito
            }
        )

        // DropdownMenu con gradiente magenta a blanco
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(330.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp) // Bordes suaves
                )
        ) {
            difficulties.forEach { difficulty ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = difficulty,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White, // Texto blanco para contraste
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    onClick = {
                        selectedText = difficulty
                        expanded = false
                    },
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp) // Compactar padding interno
                        .clip(RoundedCornerShape(6.dp)) // Esquinas suaves
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFDD6AE5),
                                    Color(0xFFAA00AA)
                                )
                            )
                        )
                )
            }
        }
    }
}
