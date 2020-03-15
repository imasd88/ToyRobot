package com.emazdoor.toyrobot.models

class Robot {

    var currentPosition: RobotPosition? = null
    private val step = 1

    fun place(position: RobotPosition) {
        currentPosition = position
    }

    fun move(): RobotPosition? {
        return currentPosition?.run {
            when (direction) {
                Directions.NORTH ->
                    RobotPosition(xPos, yPos + step, direction)
                Directions.SOUTH -> RobotPosition(xPos, yPos - step, direction)
                Directions.EAST -> RobotPosition(xPos + step, yPos, direction)
                Directions.WEST -> RobotPosition(xPos - step, yPos, direction)
            }
        }
    }

    fun rotate(commands: Commands) {
        currentPosition?.apply {
            val direction = Directions.values().indexOf(direction)
            val indexDirectionEnum = when (commands) {
                Commands.LEFT -> direction - 1
                Commands.RIGHT -> direction + 1
                else -> return
            }
            currentPosition = RobotPosition(
                xPos,
                yPos,
                Directions.values()[evalDirection(indexDirectionEnum)]
            )
        }
    }

    private fun evalDirection(direction: Int): Int {
        return direction.run {
            when {
                this < 0 -> {
                    Directions.values().count() - 1
                }
                this >= Directions.values().count() -> {
                    0
                }
                else -> this
            }
        }
    }
}