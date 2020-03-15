package com.emazdoor.toyrobot.models

class GameBoard(private val width: Int = 5, private val height: Int = 5) {

    fun isValidMoveLocation(x: Int, y: Int) = ((x in 0..width) && (y in 0..height))

    fun getWidth() = width

    fun getHeight() = height
}