package com.emazdoor.toyrobot

import com.emazdoor.toyrobot.manager.BoardManager
import com.emazdoor.toyrobot.models.*
import junit.framework.Assert.*
import org.junit.Test

class BoardManagerUnitTest {

    @Test
    fun `test command move doesn't work without command place first`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.MOVE)
        assertNull(boardManager.robot.currentPosition)
    }

    @Test
    fun `test command left doesn't work without command place first`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.LEFT)
        assertNull(boardManager.robot.currentPosition)
    }

    @Test
    fun `test command right doesn't work without command place first`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.LEFT)
        assertNull(boardManager.robot.currentPosition)
    }

    @Test
    fun `test command place should place robot at given position`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.PLACE, RobotPosition(3, 4, Directions.SOUTH))
        val currentPosition = boardManager.robot.currentPosition
        assertEquals(3, currentPosition?.xPos)
        assertEquals(4, currentPosition?.yPos)
        assertEquals(Directions.SOUTH, currentPosition?.direction)
    }

    @Test
    fun `test command move after placing robot should move robot to given position`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.PLACE, RobotPosition(3, 4, Directions.SOUTH))
        boardManager.executeCommand(Commands.MOVE)
        val currentPosition = boardManager.robot.currentPosition
        assertEquals(3, currentPosition?.xPos)
        assertEquals(3, currentPosition?.yPos)
        assertEquals(Directions.SOUTH, currentPosition?.direction)
    }
    @Test
    fun `test command left after placing robot should move left robot to given position`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.PLACE, RobotPosition(3, 4, Directions.EAST))
        boardManager.executeCommand(Commands.LEFT)
        val currentPosition = boardManager.robot.currentPosition
        assertEquals(3, currentPosition?.xPos)
        assertEquals(4, currentPosition?.yPos)
        assertEquals(Directions.SOUTH, currentPosition?.direction)
    }

}