package com.example.calculaaprovacao

import Aluno
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
    fun goNextActivity(view: View){
        val nome = findViewById<EditText>(R.id.nomeAlunoText).text.toString()
        val nota1 = findViewById<EditText>(R.id.notaUmNumber).text.toString().toDouble()
        val nota2 = findViewById<EditText>(R.id.notaDoisNumber).text.toString().toDouble()
        val frequencia = findViewById<EditText>(R.id.frequenciaNumber).text.toString().toInt()

        if(nome.isEmpty() || nota1 < 0  || nota2 < 0 || frequencia < 0){
            return
        }

        val aluno = Aluno(
            nome = nome,
            nota1 = nota1,
            nota2 = nota2,
            frequencia = frequencia
        )

        val intent = Intent(this, Activity2::class.java)
        intent.putExtra("aluno", aluno)

        startActivity(intent)
    }
}