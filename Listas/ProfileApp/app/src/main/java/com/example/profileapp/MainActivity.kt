package com.example.profileapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
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
        val image = findViewById<ImageView>(R.id.profile_image)
        val name = findViewById<TextView>(R.id.title)
        val description = findViewById<TextView>(R.id.description)
        val job = findViewById<TextView>(R.id.job_current)
        val layout = findViewById<LinearLayout>(R.id.experiences)

        name.text = "Erick Gabriel Ferreira Gaspar"
        description.text="Estudante do 6° Semestre do curso de Engenharia de Software, UFC - campus Quixadá. Experiência com o desenvolvimento front-end para aplicações web. "
        job.text="Emprego atual: estudante."

        val experience_list = listOf(
            "Desenvolvedor front-end - Empresa 1",
            "Desenvolvedor back-end - Empresa 2",
            "Desenvolvedor full stack - Empresa 3",
        )

        for (experience in experience_list){
            val view = TextView(this)
            view.text = experience
            view.textSize = 15f
            layout.addView(view)
        }

    }
}