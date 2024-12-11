package com.fanxu.hangmanmvvm.view.Final

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FinalScreen(viewModel: FinalViewModel, navController: NavController) {
    // Aquí se declara la vista @Composable de cada botón, TextField, imagen, etc.
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Hola(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Hola(modifier: Modifier, viewModel: FinalViewModel) {
    Box(modifier = modifier) {
        Text(
            text = "Final"
        )
    }
}