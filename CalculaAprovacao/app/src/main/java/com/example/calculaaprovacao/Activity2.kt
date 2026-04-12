package com.example.calculaaprovacao

import Aluno
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity2 : AppCompatActivity() {
    lateinit var nomeText : TextView
    lateinit var frequenciaText : TextView

    lateinit var mediaText : TextView

    lateinit var situacaoText : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)

        nomeText = findViewById(R.id.nomeAlunoView)
        frequenciaText = findViewById(R.id.frequenciaView)
        mediaText = findViewById(R.id.mediaView)
        situacaoText = findViewById(R.id.situacaoView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val aluno = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("aluno", Aluno::class.java)
        } else {
            intent.getSerializableExtra("aluno")
        }

        if(aluno != null){
            calcularNota(aluno as Aluno)
        }else{
            Toast.makeText(this, "Erro ao carregar aluno", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calcularNota(aluno:Aluno){
        val n1 = aluno.nota1
        val n2 = aluno.nota2
        val frequencia = aluno.frequencia

        if(n1 < 0 || n2 < 0 || frequencia < 0){
            return
        }

        val media = n1 + n2 / 2

        nomeText.text = aluno.nome
        frequenciaText.text = frequencia.toString()
        mediaText.text = media.toString()

        if(media >= 7 && frequencia >= 75){
            situacaoText.text =  "Aprovado"
        }
        if(media >= 4 && media < 6.9 && frequencia >= 75){
            situacaoText.text =  "Final"
        }
        if(frequencia < 75){
            situacaoText.text =  "Reprovado por falta"
        }
        if(media < 4){
            situacaoText.text =  "Reprovado por nota"
        }

    }
}