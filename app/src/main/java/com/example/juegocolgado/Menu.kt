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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Menu(navController: NavController) {
    // Estado para la dificultad seleccionada
    var selectedDifficulty by remember { mutableStateOf("Facil") } // Default "Facil"

    // Estado del Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }

    // Mostrar el mensaje del Snackbar si showSnackbar es verdadero
    if (showSnackbar) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar("Reglas del juego: \n1. Adivina la palabra antes de que se complete el dibujo del ahorcado. \n2. Cada intento incorrecto suma un error. \n3. Si llegas a 10 errores, pierdes el juego. \n4. Si adivinas todas las letras antes de 10 errores, ganas.")
            showSnackbar = false
        }
    }

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
            Text(
                text = "JOMMY",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA)) // Gradiente similar al de los botones
                    )
                ),
            )
            Text(
                text = "Juego de Ahorcado",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA)) // Gradiente similar al de los botones
                    )
                ),
                modifier = Modifier.padding(bottom = 20.dp) // Espacio debajo del título
            )
            Box(
                modifier = Modifier
                    .size(300.dp)
            ) {
                Image(
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(id = R.drawable.hangnn),
                    contentDescription = "Game icon",
                    modifier = Modifier.matchParentSize()
                )
            }
            // Pasar el estado de dificultad seleccionada al dropdown
            DropDownDifficulty(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFDD6AE5)),
                selectedDifficulty = selectedDifficulty,
                onDifficultySelected = { selectedDifficulty = it } // Actualiza el estado
            )
            // Botón Play, pasa la dificultad seleccionada al navegar
            Box(
                modifier = Modifier
                    .clickable { navController.navigate(Routes.getGameScreenRoute(selectedDifficulty)) }
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA))
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
            Spacer(modifier = Modifier.padding(10.dp))
            // Botón de Ayuda
            Box(
                modifier = Modifier
                    .clickable { showSnackbar = true } // Mostrar el Snackbar con las reglas
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA))
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

        // SnackbarHost
        SnackbarHost(hostState = snackbarHostState)
    }
}

// Componente DropDown para seleccionar dificultad
@Composable
fun DropDownDifficulty(
    modifier: Modifier = Modifier,
    selectedDifficulty: String,
    onDifficultySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val difficulties = listOf("Facil", "Medio", "Dificil")

    Column(modifier = modifier.padding(12.dp)) {

        OutlinedTextField(
            value = selectedDifficulty,
            onValueChange = {},
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
                .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp)),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            placeholder = {
                Text("Select Difficulty", color = Color(0xFFAA00AA))
            }
        )

        // DropdownMenu para seleccionar la dificultad
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(330.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            difficulties.forEach { difficulty ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = difficulty,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    onClick = {
                        onDifficultySelected(difficulty) // Actualiza la dificultad seleccionada
                        expanded = false
                    },
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA))
                            )
                        )
                )
            }
        }
    }
}
