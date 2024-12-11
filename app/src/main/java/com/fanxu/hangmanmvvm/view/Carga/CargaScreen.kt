package com.fanxu.hangmanmvvm.view.Carga

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CargaScreen(viewModel: CargaViewModel, navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Hola(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Hola(modifier: Modifier, viewModel: CargaViewModel) {
    Box(modifier = modifier) {
        Text(text = "Carga")
    }
}
