package com.example.calculadoraimc

import User
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

    fun goNextActivity(view: View) {
        val nome = findViewById<EditText>(R.id.textNome).text.toString()
        val pesoText = findViewById<EditText>(R.id.textPeso).text.toString()
        val alturaText = findViewById<EditText>(R.id.textAltura).text.toString()

            if (pesoText.isEmpty() || alturaText.isEmpty()) return

        val peso = pesoText.toDouble()
        val altura = alturaText.toDouble()

        val user = User(
            nome = nome,
            peso = peso,
            altura = altura,
            imc = 0.0
        )

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("user", user)

        startActivity(intent)
    }
}