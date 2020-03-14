package com.emazdoor.toyrobot.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.emazdoor.toyrobot.databinding.ActivityMainBinding
import com.emazdoor.toyrobot.listener.BoardManagerListener
import com.emazdoor.toyrobot.manager.BoardManager
import com.emazdoor.toyrobot.models.Directions
import com.emazdoor.toyrobot.models.GameBoard
import com.emazdoor.toyrobot.models.Robot
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), BoardManagerListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var boardManager: BoardManager
    private val boardSize = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        boardManager = BoardManager(GameBoard(boardSize, boardSize), Robot())
        setContentView(binding.root)
        boardManager.setBoardManagerListener(this)
        binding.apply {
            runButton.setOnClickListener {
                processCommand()
            }

            commandET.setOnEditorActionListener { v, _, _ ->
                hideKeyboard(v)
                processCommand()
                true
            }

        }
        updateOutputView()
    }

    private fun hideKeyboard(v: View) {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            v.windowToken,
            0
        )
    }

    private fun processCommand() {
        boardManager.processUserCommand(commandET.text.toString().toUpperCase(Locale.US))
        commandET.editableText.clear()
    }

    override fun updatePositionDetail(x: Int, y: Int, direction: Directions) {
//        binding.outputTv.text = "X: $x, Y: $y, F: ${direction.name}"
        updateOutputView(x, y, direction)
    }

    private fun updateOutputView(x: Int = 0, y: Int = 0, direction: Directions = Directions.NORTH) {
        binding.outputTv.text = "X: $x, Y: $y, F: ${direction.name}"
    }
}
