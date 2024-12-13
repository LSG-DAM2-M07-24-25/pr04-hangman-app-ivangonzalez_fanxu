package com.example.juegocolgado

object Routes {
    val StartScreen = "startScreen"
    val MenuScreen = "menuScreen"
    val GameScreen = "gameScreen/{difficulty}"
    val EndScreen = "endScreen/{result}/{word}"

    fun getGameScreenRoute(difficulty: String): String {
        return "gameScreen/$difficulty"
    }
}

