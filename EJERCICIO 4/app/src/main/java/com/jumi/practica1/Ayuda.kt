package com.jumi.practica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Ayuda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda)

        val botonSalir: Button = findViewById(R.id.btnVolver)
        botonSalir.setOnClickListener{onClick()}
    }

    private fun onClick() {
        finish()
    }
}