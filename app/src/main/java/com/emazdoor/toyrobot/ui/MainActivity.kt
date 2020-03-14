package com.emazdoor.toyrobot.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emazdoor.toyrobot.R
import com.emazdoor.toyrobot.databinding.ActivityMainBinding
import com.emazdoor.toyrobot.manager.BoardManager
import com.emazdoor.toyrobot.models.GameBoard
import com.emazdoor.toyrobot.models.Robot
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var boardManager: BoardManager
    val boardSize = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        boardManager = BoardManager(GameBoard(boardSize, boardSize), Robot())
        setContentView(binding.root)

        binding.apply {
            runButton.setOnClickListener {
                boardManager.processUserCommand(command.text.toString().toUpperCase(Locale.US))
            }
        }
    }

    //a function to act on the user input
}
