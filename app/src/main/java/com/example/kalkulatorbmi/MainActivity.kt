package com.example.kalkulatorbmi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        val waga = findViewById<EditText>(R.id.waga)
        val wzrost = findViewById<EditText>(R.id.wzrost)
        val oblicz = findViewById<Button>(R.id.oblicz)
        val wynik = findViewById<TextView>(R.id.wynik)

        oblicz.setOnClickListener {
            val weightStr = waga.text.toString()
            val heightStr = wzrost.text.toString()

            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
                val weight = weightStr.toFloat()
                val height = heightStr.toFloat() / 100

                val bmi = weight / (height * height)
                val formattedBmi = String.format("%.2f", bmi)

                var description = ""
                if (bmi < 18.5) {
                    description = "Niedowaga"
                } else if (bmi < 25) {
                    description = "Waga prawidlowa"
                } else if (bmi < 30) {
                    description = "Nadwaga"
                } else {
                    description = "Otylosc"
                }

                wynik.text = "Wynik: " + formattedBmi + " (" + description + ")"
            } else {
                wynik.text = "Wprowadz prawid;owe dane"
            }
        }
    }
}