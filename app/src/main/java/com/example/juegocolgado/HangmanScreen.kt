package com.example.juegocolgado

sealed class Routes(val route: String) {
    object StartScreen : Routes("startscreen")
    object MenuScreen : Routes("menuscreen")
    object GameScreen : Routes("gamescreen")
    object EndScreen : Routes("endscreen")
}
