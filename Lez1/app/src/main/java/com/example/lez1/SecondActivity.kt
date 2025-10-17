package com.example.lez1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.MainScope
import android.widget.SeekBar


var score = 0;

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonLogout = findViewById<Button>(R.id.buttonLogout)

        var pointText = findViewById<TextView>(R.id.tvPoints)
        var plusButton = findViewById<Button>(R.id.btnPlus)
        var minusButton = findViewById<Button>(R.id.btnMinus)

        var seekbar = findViewById<SeekBar>(R.id.seekbar)
        seekbar.max = 10
        seekbar.progress = 5                // parte da 0 (perché 5 - 5 = 0)

        var progressText = findViewById<TextView>(R.id.tvVote)
        progressText.text = "0"


        buttonLogout.setOnClickListener {
            logout()
        }

        plusButton.setOnClickListener { add(pointText) }
        minusButton.setOnClickListener { minus(pointText) }
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            //overridare onProgressChanged
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress - 5   // mappa 0..10 → -5..+5
                progressText.text = value.toString()
            }

            //overridare onStartTrackingTouch
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            //overridare onStopTrackingTouch
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })
    }

    fun logout() {
        //azzero lo score perchè altrimenti anche se segna 0 all'inizio, rimane memorizzato il valore precedente
        score = 0

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun add(points : TextView) {
        score++
        points.text = "Punti esperienza : $score"
    }

    fun minus(points : TextView) {
        if (score > 0)
            score--
        points.text = "Punti esperienza : $score"
    }
}