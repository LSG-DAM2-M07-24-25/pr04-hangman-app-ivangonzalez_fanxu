package com.example.juegocolgado

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun Start(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.White, Color.LightGray))) // Fondo blanco a gris
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Game icon image
            Box(
                modifier = Modifier
                    .size(200.dp)
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

            // Fake progress bar
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                FakeProgressBar(navController)
            }
        }
    }
}

@Composable
fun FakeProgressBar(navController: NavController) {
    var progressStatus by remember { mutableFloatStateOf(0f) }

    // Update the progress status in a loop with a delay
    LaunchedEffect(Unit) {
        while (progressStatus < 0.8f) {
            delay(50)
            progressStatus += 0.03f
        }
        delay(600)
        progressStatus -= 0.1f
        while (progressStatus < 1f) {
            delay(50)
            progressStatus += 0.01f
        }
        navController.navigate(Routes.MenuScreen)
    }

    // Progress bar UI
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LinearProgressIndicator(
            progress = { progressStatus },
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth(),
            color = Color.Magenta,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Butt,
        )
    }
}
