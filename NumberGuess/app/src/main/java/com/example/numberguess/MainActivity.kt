package com.example.numberguess

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.format.TextStyle

class MainActivity : AppCompatActivity() {
    private var sortedNumber: Int = 0
    private var tries: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sortedNumber = (1..100).random()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun guess (view: View){
        val input = findViewById<EditText>(R.id.editTextInput)
        val output = findViewById<TextView>(R.id.textViewOutput)
        var tip : String

        var inputNum : Int = input.text.toString().toInt()

        tries++;

        if(sortedNumber < inputNum){
            tip = "Número sorteado é menor!"
        } else if(sortedNumber > inputNum){
            tip = "Numero sorteado é maior!"
        } else{
            tip = "Você acertou!!\n Você usou $tries tentativas"
        }
        output.text = tip

    }
}