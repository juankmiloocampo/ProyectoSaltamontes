package com.jumi.practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val miBoton: Button = findViewById(R.id.btnRegistro)
        miBoton.setOnClickListener {onClick(1)}

        val miBoton2: Button = findViewById(R.id.btnEstadisticas)
        miBoton2.setOnClickListener{onClick(2)}

        val miBoton3: Button = findViewById(R.id.btnAyuda)
        miBoton3.setOnClickListener{onClick(3)}
    }

    private fun onClick(boton: Int) {
        when(boton){
            1 ->{
                val intent = Intent(this, calcularPromedio ::class.java)
                startActivity(intent)
            }
            2 ->{
                val intent = Intent(this, Estadistica ::class.java)
                startActivity(intent)
            }
            3 ->{
                val intent = Intent(this, Ayuda ::class.java)
                startActivity(intent)
            }
        }
    }
}