package com.example.heroeslist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MasterActivity : AppCompatActivity() {
    private lateinit var recyclerViewHeroes: RecyclerView
    private fun createHeroes(): List<Hero> {
        return listOf(
            Hero(
                R.drawable.flash,
                "Flash",
                "DC",
                listOf("Super velocidade", "Reflexos rápidos", "Cura acelerada")
            ),
            Hero(
                R.drawable.batman,
                "Batman",
                "DC",
                listOf("Inteligência", "Combate", "Tecnologia")
            ),
            Hero(
                R.drawable.superman,
                "Superman",
                "DC",
                listOf("Força", "Voo", "Visão de calor")
            ),
            Hero(
                R.drawable.hulk,
                "Hulk",
                "Marvel",
                listOf("Super força", "Regeneração")
            ),
            Hero(
                R.drawable.ironman,
                "Ironman",
                "Marvel",
                listOf("Armadura tecnológica", "Voo", "Armas")
            ),
            Hero(
                R.drawable.drstrange,
                "Dr. Strange",
                "Marvel",
                listOf("Magia", "Manipulação do tempo")
            )
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_master)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerViewHeroes = findViewById(R.id.heroesRV)
        recyclerViewHeroes.adapter = HeroesAdapter(this.createHeroes(), this, {
            Toast.makeText(this, "Hero: ${it.heroName}", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Details::class.java)
            intent.putExtra("HERO", it)

            startActivity(intent)
        })
        recyclerViewHeroes.layoutManager = LinearLayoutManager(this)
        recyclerViewHeroes.setHasFixedSize(true)
        recyclerViewHeroes.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }
}