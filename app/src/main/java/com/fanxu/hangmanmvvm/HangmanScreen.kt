package com.fanxu.hangmanmvvm

object Routes {
    val StartScreen = Route("start")
    val Menu = Route("menu")
    val Juego = Route("juego")
    val Final = Route("final")
}

data class Route(val route: String)
