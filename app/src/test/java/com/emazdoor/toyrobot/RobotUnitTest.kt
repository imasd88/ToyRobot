package com.emazdoor.toyrobot

import com.emazdoor.toyrobot.models.Commands
import com.emazdoor.toyrobot.models.Directions
import com.emazdoor.toyrobot.models.Robot
import com.emazdoor.toyrobot.models.RobotPosition
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RobotUnitTest {

    private val robot = Robot()

    @Test
    fun `test place() places robot on given position should save the position`() {
        robot.place(RobotPosition(1, 2, Directions.EAST))
        assertNotNull(robot.currentPosition)
        assertEquals(1, robot.currentPosition?.xPos)
        assertEquals(2, robot.currentPosition?.yPos)
        assertEquals(Directions.EAST, robot.currentPosition?.direction)
    }

    @Test
    fun `test move() moves robot to given position should move the robot`() {
        robot.place(RobotPosition(4, 5, Directions.SOUTH))
        robot.place(robot.move()!!)
        assertEquals(4, robot.currentPosition?.yPos)
    }

    @Test
    fun `test rotate() does not react to move command`() {
        robot.rotate(Commands.MOVE)
        assertNull(robot.currentPosition)
    }

    @Test
    fun `test rotate() does not rotate to Left if robot no placed`() {
        robot.rotate(Commands.LEFT)
        assertNull(robot.currentPosition)
    }

    @Test
    fun `test rotate() rotates robot when called after place()`() {
        robot.place(RobotPosition(1, 2, Directions.NORTH))
        robot.rotate(Commands.LEFT)
        assertEquals(Directions.WEST, robot.currentPosition?.direction)
    }

    @Test
    fun `test move() does not move the robot if not placed first`() {
        robot.move()
        assertNull(robot.currentPosition)
    }
}