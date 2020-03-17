package com.emazdoor.toyrobot.listener

import com.emazdoor.toyrobot.models.Directions

interface BoardManagerListener {
    fun updatePositionDetail(x: Int, y: Int, direction: Directions)
    fun reportError(message: String)
}