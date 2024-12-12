import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Game(targetWord: String, navController: NavController) {
    var guessedLetters by remember { mutableStateOf(setOf<Char>()) }

    // Construimos la palabra oculta
    var displayWord by remember {
        mutableStateOf(
            targetWord.map { letter ->
                if (letter in guessedLetters) letter else '_'
            }.joinToString(" ")
        )
    }

    // Estado para el texto editable con guiones bajos
    var textFieldValue by remember { mutableStateOf(displayWord) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center) // Centrado vertical y horizontal
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Alineado al centro horizontalmente
        ) {
            Text(
                text = "Juego Colgado",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                modifier = Modifier.background(color = Color.Transparent) // Usamos Color.Transparent
            )

            TextField(
                value = textFieldValue,
                onValueChange = {}, // Este campo es solo de lectura para mostrar la palabra
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 20.dp)// Ajustamos el tamaño para que no ocupe todo el ancho
                    .height(86.dp)
                    .align(Alignment.CenterHorizontally), // Aseguramos que el TextField se centre horizontalmente
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 34.sp, textAlign = TextAlign.Center),  // Agrandar el texto
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent, // Sin color de fondo
                    focusedIndicatorColor = Color.Transparent, // Indicador transparente cuando tiene foco
                    unfocusedIndicatorColor = Color.Transparent, // Indicador transparente cuando no tiene foco
                )
            )
            // Crear un contenedor con desplazamiento en cuadrícula (LazyVerticalGrid)
            LazyVerticalGrid(
                columns = GridCells.Fixed(6), // Definir 6 columnas
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(('A'..'Z').toList()) { letter -> // Aquí usamos items con una lista
                    val isLetterGuessed = letter.lowercaseChar() in guessedLetters
                    Button(
                        onClick = {
                            if (!isLetterGuessed) {
                                guessedLetters = guessedLetters + letter.lowercaseChar()
                                // Actualizar la palabra oculta
                                displayWord = targetWord.map { letterInWord ->
                                    if (letterInWord in guessedLetters) letterInWord else '_'
                                }.joinToString(" ")
                                textFieldValue = displayWord
                            }
                        },
                        enabled = !isLetterGuessed, // Deshabilitar el botón si la letra ya fue adivinada
                        modifier = Modifier
                            .padding(4.dp)
                            .size(48.dp), // Hacer los botones más cuadrados
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White, // Fondo blanco
                            contentColor = Color.Black // Texto negro
                        ),
                        border = BorderStroke(1.dp, Color.Black), // Borde negro
                        shape = MaterialTheme.shapes.medium // Bordes redondeados
                    ) {
                        Text(letter.toString())
                    }
                }
            }

            // Botón para reiniciar el juego
            Button(
                onClick = {
                    guessedLetters = setOf()
                    displayWord = targetWord.map { '_' }.joinToString(" ")
                    textFieldValue = displayWord
                },
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White, // Fondo blanco
                    contentColor = Color.Black // Texto negro
                ),
                border = BorderStroke(1.dp, Color.Black), // Borde negro
                shape = MaterialTheme.shapes.medium // Bordes redondeados
            ) {
                Text("Reiniciar Juego")
            }
        }
    }
}
