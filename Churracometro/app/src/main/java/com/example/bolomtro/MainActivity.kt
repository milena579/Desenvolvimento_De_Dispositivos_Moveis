package com.example.bolomtro

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
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

        var sbMen =  findViewById<SeekBar>(R.id.seekBarMen)
        var menQtt =  findViewById<TextView>(R.id.textViewMen)

        var sbWom =  findViewById<SeekBar>(R.id.seekBarWomen)
        var womQtt =  findViewById<TextView>(R.id.textViewWomen)

        sbMen.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                menQtt.text = progress.toString()
                calculate( progress, sbWom.progress,)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        sbWom.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                womQtt.text = progress.toString()
                calculate(sbMen.progress, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }

    fun calculate(menQtt:Int, womQtt:Int){
        val outpuSussage = findViewById<TextView>(R.id.outputSausage)
        val outputMeat = findViewById<TextView>(R.id.outputMeat)

        val susageQtt: Double = (menQtt * 250.0 + womQtt * 150.0)/1000
        val meatQtt: Double = (menQtt * 450.0 + womQtt * 300.0)/1000

        outpuSussage.text = susageQtt.toString() + "Kg"
        outputMeat.text = meatQtt.toString() + "Kg"
    }
}