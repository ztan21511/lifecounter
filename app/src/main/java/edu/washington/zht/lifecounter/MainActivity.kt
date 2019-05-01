package edu.washington.zht.lifecounter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initializePlayer(player1, player1Lives, player1PlusOne, player1MinusOne, player1PlusFive, player1MinusFive)
        initializePlayer(player2, player2Lives, player2PlusOne, player2MinusOne, player2PlusFive, player2MinusFive)
        initializePlayer(player3, player3Lives, player3PlusOne, player3MinusOne, player3PlusFive, player3MinusFive)
        initializePlayer(player4, player4Lives, player4PlusOne, player4MinusOne, player4PlusFive, player4MinusFive)
    }

    data class Player (
        val name: TextView,
        val livesLeft: TextView
    )

    fun initializePlayer(name: TextView, lives: TextView, add1: Button, minus1: Button, add5: Button, minus5: Button) {
        val player = Player(name, lives)
        inputEvent(player, add1, 1)
        inputEvent(player, minus1, -1)
        inputEvent(player, add5, 5)
        inputEvent(player, minus5, -5)
    }

    fun inputEvent(player: Player, updateScore: Button, value: Int) {
        updateScore.setOnClickListener{
            val lives = changeLife(player.livesLeft, value)
            player.livesLeft.setText(lives.lifeCount)
            if(lives.lost) {
                messageToast(player.name.text.toString())
            }
        }
    }

    fun messageToast(playerName: String) {
        Toast.makeText(this, playerName + " LOSES!", Toast.LENGTH_LONG).show()
    }

    data class checkLost (
        val lifeCount: String,
        val lost: Boolean
    )

    fun changeLife(lives: TextView, livesChange: Int) : checkLost {
        val newLife = updateScore(lives.text.toString().toInt(), livesChange)
        return checkLost(newLife.toString(), checkGameOver(newLife))
    }

    fun checkGameOver(newScore: Int): Boolean {
        return newScore == 0
    }

    fun updateScore(lives:Int, newLives: Int): Int {
        if(lives + newLives < 0) {
            return 0
        }
        return lives + newLives
    }

}
