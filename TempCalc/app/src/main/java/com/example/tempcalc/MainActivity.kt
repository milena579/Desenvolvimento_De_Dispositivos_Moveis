package com.example.tempcalc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun converter(view: View){
        val input = findViewById<EditText>(R.id.editTextNumber)
        val out = findViewById<TextView>(R.id.textViewOutput)
        val toCelsius = findViewById<RadioButton>(R.id.radioButtonToCelsius)

        if(input.length() == 0){
            Toast.makeText(this, "Forneça a temperatura", Toast.LENGTH_SHORT).show()
            out.text = ""
        } else {
            val tempInput = input.text.toString().toDouble()
            var tempOut = 0.00

            if(toCelsius.isChecked){
                tempOut = (tempInput - 32) * 5/9
                out.text = "Resultado em celsius: $tempOut"
            } else{
                tempOut = tempInput * 9 / 5 + 32
                out.text = "Resultado em fahreinheit: $tempOut"
            }
        }
    }

}