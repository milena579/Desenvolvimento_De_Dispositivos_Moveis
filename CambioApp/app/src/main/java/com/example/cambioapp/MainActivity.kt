package com.example.cambioapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.EditText
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

    fun convert(view: View){
        val valor_dolar = 5.5

        val input = findViewById<EditText>(R.id.editTextInput)
        val output = findViewById<TextView>(R.id.textViewOutput)

        if (input.length() == 0) {
            Toast.makeText(this, "É necessário inserir um valor!", Toast.LENGTH_SHORT).show();
            output.text = ""
        }else{

            var input_convetido = input.text.toString().toDouble()

            val real =  input_convetido * valor_dolar
            output.text = "$real reais"
        }

    }
}