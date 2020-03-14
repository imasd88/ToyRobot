package com.emazdoor.toyrobot.manager

import android.util.Log
import com.emazdoor.toyrobot.has
import com.emazdoor.toyrobot.listener.BoardManagerListener
import com.emazdoor.toyrobot.models.*
import java.lang.Exception

class BoardManager(val board: GameBoard, val robot: Robot) {

    private lateinit var boardManagerListener: BoardManagerListener

    fun processUserCommand(command: String) {
        if (command has "MOVE") executeCommand(Commands.MOVE)

        if (command has "PLACE") {
            if (command has " ") {
                val commandString = command.split(" ")[1].split(",")
                try {
                    val x = commandString[0].toInt()
                    val y = commandString[1].toInt()
                    val direction = commandString[2]
                    val updatedPosition = RobotPosition(x, y, Directions.valueOf(direction))
                    executeCommand(Commands.PLACE, updatedPosition)
                } catch (e: Exception) {
//                    return "Something went wrong" //ahmed: change this
                }
            }
        } else if (command has "LEFT")
            executeCommand(Commands.LEFT)
        else if (command has "RIGHT")
            executeCommand(Commands.RIGHT)
        else if (command has "REPORT")
            executeCommand(Commands.REPORT)
        else
            return /*"wrong command"*/
//        return null

    }

    fun executeCommand(command: Commands, position: RobotPosition? = null) {
        Log.e("ASD", "Command: $command")
        when (command) {
            Commands.PLACE -> {
                position?.run {
                    if (board.isValidMoveLocation(xPos, yPos))
                        robot.place(this)
                }
            }
            Commands.MOVE -> {
                robot.move()?.run {
                    if (board.isValidMoveLocation(xPos, yPos))
                        robot.place(this)
                }
            }
            Commands.LEFT, Commands.RIGHT -> {
                robot.rotate(command)
            }
            Commands.REPORT -> {
                Log.e("ASD", "REPORT")
                if (this::boardManagerListener.isInitialized)
                    robot.currentPosition?.apply {
                        boardManagerListener.updatePositionDetail(xPos, yPos, direction)
                    }
            }

        }
    }

    fun setBoardManagerListener(listener: BoardManagerListener) {
        boardManagerListener = listener
    }

}