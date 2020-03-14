package com.emazdoor.toyrobot.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emazdoor.toyrobot.R
import com.emazdoor.toyrobot.databinding.ActivityMainBinding
import com.emazdoor.toyrobot.listener.BoardManagerListener
import com.emazdoor.toyrobot.manager.BoardManager
import com.emazdoor.toyrobot.models.Directions
import com.emazdoor.toyrobot.models.GameBoard
import com.emazdoor.toyrobot.models.Robot
import java.util.*

class MainActivity : AppCompatActivity(), BoardManagerListener {

    lateinit var binding: ActivityMainBinding
    lateinit var boardManager: BoardManager
    private val boardSize = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        boardManager = BoardManager(GameBoard(boardSize, boardSize), Robot())
        setContentView(binding.root)
        boardManager.setBoardManagerListener(this)
        binding.apply {
            runButton.setOnClickListener {
                boardManager.processUserCommand(command.text.toString().toUpperCase(Locale.US))
            }
        }
        updateOutputView()
    }

    override fun updatePositionDetail(x: Int, y: Int, direction: Directions) {
//        binding.outputTv.text = "X: $x, Y: $y, F: ${direction.name}"
        updateOutputView(x, y, direction)
    }

    fun updateOutputView(x: Int = 0, y: Int = 0, direction: Directions = Directions.NORTH) {
        binding.outputTv.text = "X: $x, Y: $y, F: ${direction.name}"
    }
}
