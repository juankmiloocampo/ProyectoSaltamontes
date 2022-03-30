package com.jumi.practica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Estadistica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadistica)

        val botonSalir: Button = findViewById(R.id.btnVolver)
        botonSalir.setOnClickListener{onClick()}

        val campoTexto: TextView = findViewById(R.id.txtEstadisticas)
        var valores = DataManager.get().datosEstadistica

        if (valores == null || valores.count() < 1){
            campoTexto.text = "No existen registros aún"
        }else {
            campoTexto.text = "- Cantidad de Usuarios registrados ${valores?.get(0)}\n- Cantidad de usuarios que ganan ${valores?.get(1)}\n- Cantidad de usuarios que pierden ${valores?.get(2)}\n- Cantidad de usuarios que pueden recuperar ${valores?.get(3)}"
        }
    }

    private fun onClick() {
        finish()
    }
}