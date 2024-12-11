package com.fanxu.hangmanmvvm.view.Menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fanxu.hangmanmvvm.view.Juego.Hola

@Composable
fun MenuScreen(viewModel: MenuViewModel, navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Hola(Modifier.align(Alignment.Center), viewModel)
    }
}
@Composable
fun Hola(modifier: Modifier, viewModel: MenuViewModel) {
    Box(modifier = modifier) {
        Text(
            text = "Menu"
        )
    }
}