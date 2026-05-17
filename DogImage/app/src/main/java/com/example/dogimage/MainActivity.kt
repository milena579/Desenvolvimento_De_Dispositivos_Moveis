package com.example.dogimage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var etBreed: EditText
    private lateinit var btnGetImage: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var ivDog: ImageView
    private lateinit var dogApi: DogApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etBreed = findViewById(R.id.etBreed)
        btnGetImage = findViewById(R.id.btnGetImage)
        progressBar = findViewById(R.id.progressBar)
        ivDog = findViewById(R.id.ivDog)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        dogApi = retrofit.create(DogApi::class.java)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun getImage(view: View){
        val breed = etBreed.text.toString()

        if(breed.isNotEmpty()){
            getImageApi(breed)
        }else{
            Toast.makeText(this, "Digite uma raça", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageApi(breed: String){
        lifecycleScope.launch {
            try {
                showProgressBar()
                val response = withContext(Dispatchers.IO){
                    dogApi.getRandomDogImage(breed)
                }
                hideProgressBar()

                if(response.status == "success"){
                    Picasso.get().load(response.message).into(ivDog)
                }else{
                    Toast.makeText(this@MainActivity, "Falha ao buscar imagem", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception){
                Log.e("MainActivity", "Erro ao buscar imagem", e)
                Toast.makeText(this@MainActivity, "Erro ao buscar imagem", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
        ivDog.visibility = View.GONE
    }

    private fun hideProgressBar(){
        progressBar.visibility = View.GONE
        ivDog.visibility = View.VISIBLE
    }
}