package com.example.calculadoraimc

import User
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    lateinit var textIMC: TextView
    lateinit var textFaixa: TextView
    lateinit var textImcMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        textIMC = findViewById(R.id.textIMC)
            textFaixa = findViewById(R.id.textFaixa)
        textImcMessage = findViewById(R.id.textPesoMessage)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("user", User::class.java)
        } else {
            intent.getParcelableExtra("user")
        }

        if (user != null) {
            displayIMCResult(user)
        }
    }

    private fun displayIMCResult(user: User) {
        val peso = user.peso
        val altura = user.altura

        if (altura <= 0.0) return

        val alturaFinal = if (altura > 3) altura / 100 else altura

        val imc = peso / (alturaFinal * alturaFinal)

        textIMC.text = String.format("%.1f", imc)

        val (faixa, mensagem) = when {
            imc < 15.0 -> "Abaixo de 15" to "Extremamente abaixo do peso"
            imc < 16.0 -> "Entre 15 e 16" to "Gravemente abaixo do peso"
            imc < 18.5 -> "Entre 16 e 18,5" to "Abaixo do peso ideal"
            imc < 25.0 -> "Entre 18,5 e 25" to "Faixa de peso ideal"
            imc < 30.0 -> "Entre 25 e 30" to "Sobrepeso"
            imc < 35.0 -> "Entre 30 e 35" to "Obesidade grau I"
            imc < 40.0 -> "Entre 35 e 40" to "Obesidade grau II (grave)"
            else -> "Acima de 40" to "Obesidade grau III (mórbida)"
        }

        textFaixa.text = faixa
        textImcMessage.text = mensagem
    }
}