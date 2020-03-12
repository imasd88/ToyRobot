package com.emazdoor.toyrobot.models

class Robot {

    lateinit var currentPosition: RobotPosition

    fun place(position: RobotPosition){
        currentPosition = position
    }
}