import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.juegocolgado.Palabra
import com.example.juegocolgado.R
import com.example.juegocolgado.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Game(targetWord: String, navController: NavController, difficulty: String) {
    val palabra = remember {
        when (difficulty) {
            "Facil" -> Palabra.Facil.random()
            "Medio" -> Palabra.Medio.random()
            "Dificil" -> Palabra.Dificil.random()
            else -> Palabra.Facil.random()
        }
    }

    var guessedLetters by remember { mutableStateOf(setOf<Char>()) }
    var errors by remember { mutableStateOf(0) }
    var gameEnded by remember { mutableStateOf(false) }

    var displayWord by remember {
        mutableStateOf(
            palabra.map { letter -> if (letter in guessedLetters) letter else '_' }.joinToString(" ")
        )
    }

    var textFieldValue by remember { mutableStateOf(displayWord) }

    val images = listOf(
        R.drawable.hangman_1,
        R.drawable.hangman_2,
        R.drawable.hangman_3,
        R.drawable.hangman_4,
        R.drawable.hangman_5,
        R.drawable.hangman_6,
        R.drawable.hangman_7,
        R.drawable.hangman_8,
        R.drawable.hangman_9,
        R.drawable.hangman_10,
        R.drawable.hangman_11
    )

    if (!gameEnded) {
        if (errors == images.size - 1) {
            gameEnded = true
            navController.navigate("${Routes.EndScreen}/lose/${palabra}")
        } else if (displayWord.replace(" ", "") == palabra) {
            gameEnded = true
            navController.navigate("${Routes.EndScreen}/win/${palabra}")
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
                text = "Juego Colgado",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA)) // Gradiente similar al de los botones
                    )
                ),
                color = Color.Black
            )
            Image(
                painter = painterResource(id = images[errors]),
                contentDescription = "Estado del juego",
                modifier = Modifier.size(200.dp)
            )
            OutlinedTextField(
                value = textFieldValue,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 20.dp)
                    .height(86.dp)
                    .align(Alignment.CenterHorizontally),
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 34.sp, textAlign = TextAlign.Center),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(6),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(('A'..'Z').toList()) { letter ->
                    val isLetterGuessed = letter.lowercaseChar() in guessedLetters
                    Button(
                        onClick = {
                            if (!isLetterGuessed) {
                                guessedLetters = guessedLetters + letter.lowercaseChar()
                                if (letter.lowercaseChar() !in palabra) {
                                    errors = (errors + 1).coerceAtMost(images.size - 1)
                                }
                                displayWord = palabra.map { letterInWord ->
                                    if (letterInWord in guessedLetters) letterInWord else '_'
                                }.joinToString(" ")
                                textFieldValue = displayWord.uppercase()
                            }
                        },
                        enabled = !isLetterGuessed,
                        modifier = Modifier
                            .padding(4.dp)
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(containerColor = if (!isLetterGuessed) Color(0xFFAA00AA) else Color.Transparent) // Morado si no ha sido seleccionado
                    ) {
                        Text(
                            text = letter.toString(),
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = if (!isLetterGuessed) Color.White else Color.Gray // Color del texto blanco si no ha sido seleccionado
                        )
                    }
                }
            }

            // Botón de reiniciar juego con gradiente
            Button(
                onClick = {
                    guessedLetters = setOf()
                    errors = 0
                    val nuevaPalabra = when (difficulty) {
                        "Facil" -> Palabra.Facil.random()
                        "Medio" -> Palabra.Medio.random()
                        "Dificil" -> Palabra.Dificil.random()
                        else -> Palabra.Facil.random()
                    }
                    displayWord = nuevaPalabra.map { '_' }.joinToString(" ")
                    textFieldValue = displayWord.uppercase()
                    gameEnded = false
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.horizontalGradient(listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA)))),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Reiniciar Juego",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }

            // Botón de salir de la partida con gradiente
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Navegar al menú
                    navController.navigate(Routes.MenuScreen)
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.horizontalGradient(listOf(Color(0xFFDD6AE5), Color(0xFFAA00AA)))),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Salir",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
        }
    }
}
