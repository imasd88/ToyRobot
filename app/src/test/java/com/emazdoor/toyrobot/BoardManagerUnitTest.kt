package com.emazdoor.toyrobot

import com.emazdoor.toyrobot.manager.BoardManager
import com.emazdoor.toyrobot.models.*
import junit.framework.Assert.*
import org.junit.Assert.assertNotEquals
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
        assertEquals(Directions.NORTH, currentPosition?.direction)
    }

    @Test
    fun `test command right after placing robot should move right robot to given position`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.PLACE, RobotPosition(3, 4, Directions.EAST))
        boardManager.executeCommand(Commands.RIGHT)
        val currentPosition = boardManager.robot.currentPosition
        assertEquals(3, currentPosition?.xPos)
        assertEquals(4, currentPosition?.yPos)
        assertEquals(Directions.SOUTH, currentPosition?.direction)
    }

    @Test
    fun `test move command to move the robot outside the bounds should not move`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.PLACE, RobotPosition(5, 5, Directions.EAST))
        boardManager.executeCommand(Commands.MOVE)
        val currentPosition = boardManager.robot.currentPosition
        assertNotEquals(6, currentPosition?.xPos)
    }

    @Test
    fun `test place robot at north and rotate right should direct at east`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.PLACE, RobotPosition(5, 5, Directions.NORTH))
        boardManager.executeCommand(Commands.RIGHT)
        val currentPosition = boardManager.robot.currentPosition
        assertEquals(Directions.EAST, currentPosition?.direction)
    }

    @Test
    fun `test place robot at north and rotate right and then rotate left should direct at north`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.PLACE, RobotPosition(5, 5, Directions.NORTH))
        boardManager.executeCommand(Commands.RIGHT)
        boardManager.executeCommand(Commands.LEFT)
        val currentPosition = boardManager.robot.currentPosition
        assertEquals(Directions.NORTH, currentPosition?.direction)
    }
    @Test
    fun `test rotations and moves from east`() {
        val boardManager = BoardManager(GameBoard(), Robot())
        boardManager.executeCommand(Commands.PLACE, RobotPosition(1, 2, Directions.EAST))
        boardManager.executeCommand(Commands.MOVE)
        boardManager.executeCommand(Commands.MOVE)
        boardManager.executeCommand(Commands.LEFT)
        boardManager.executeCommand(Commands.MOVE)
        val currentPosition = boardManager.robot.currentPosition
        assertEquals(Directions.NORTH, currentPosition?.direction)
        assertEquals(3, currentPosition?.xPos)
        assertEquals(3, currentPosition?.yPos)
    }

}