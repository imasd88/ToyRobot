package com.emazdoor.toyrobot

import com.emazdoor.toyrobot.models.GameBoard
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameBoardUnitTest {

    private val gameBoard = GameBoard()

    @Test
    fun `test isValidMoveLocation with negative x value should return false`() {
        assertFalse(gameBoard.isValidMoveLocation(-1, 0))
    }

    @Test
    fun `test isValidMoveLocation with negative y value should return false`() {
        assertFalse(gameBoard.isValidMoveLocation(0, -1))
    }

    @Test
    fun `test isValidMoveLocation with negative x and y value should return false`() {
        assertFalse(gameBoard.isValidMoveLocation(-1, -1))
    }

    @Test
    fun `test isValidMoveLocation with positive x above board size should return false`() {
        assertFalse(gameBoard.isValidMoveLocation(6, 4))
    }

    @Test
    fun `test isValidMoveLocation with positive y value above board size should return false`() {
        assertFalse(gameBoard.isValidMoveLocation(4, 6))
    }

    @Test
    fun `test isValidMoveLocation with positive x and y value above board size should return false`() {
        assertFalse(gameBoard.isValidMoveLocation(6, 6))
    }

    @Test
    fun `test isValidMoveLocation with largest valid x and y value should return true`() {
        assertTrue(gameBoard.isValidMoveLocation(gameBoard.getWidth(), gameBoard.getHeight()))
    }

    @Test
    fun `test isValidMoveLocation with smallest valid x and y value should return true`() {
        assertTrue(gameBoard.isValidMoveLocation(0, 0))
    }

}
