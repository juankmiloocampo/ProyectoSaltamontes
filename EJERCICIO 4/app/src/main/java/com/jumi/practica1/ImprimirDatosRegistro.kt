package com.jumi. practica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColorInt
import java.util.ArrayList

class ImprimirDatosRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imprimir_datos_registro)

        val botonSalir: Button = findViewById(R.id.btnVolver)
        botonSalir.setOnClickListener{onClick()}

        val campoMensaje=findViewById<TextView>(R.id.txtDatosUsuario)

        var miBundle: Bundle? = this.intent.extras

        if(miBundle != null){
            var lista: ArrayList<String>? = miBundle.getStringArrayList("abc")
            var definitiva= " "
            if(lista?.get(15) == "true"){
                definitiva = "Ha ganado la materia con un promedio de ${lista?.get(17)}"
            }
            else
            {
                definitiva = "Ha perdido la materia con un promedio de ${lista?.get(17)} "
                if(lista?.get(16) == "true") definitiva+= "pero puede recuperar"
                else definitiva+= "y no puede recuperar"
            }
            var mensaje = "Documento: ${lista?.get(0)}\nNombre Completo: ${lista?.get(1)}\nDireccion: ${lista?.get(2)}\nEdad: ${lista?.get(3)}\nTelefono: ${lista?.get(4)}\n- Materia: ${lista?.get(5)} nota: ${lista?.get(6)}\n- Materia: ${lista?.get(7)} nota: ${lista?.get(8)}\n- Materia: ${lista?.get(9)} nota: ${lista?.get(10)}\n- Materia: ${lista?.get(11)} nota: ${lista?.get(12)}\n Materia: ${lista?.get(13)} nota: ${lista?.get(14)}\n $definitiva"
            campoMensaje.text = mensaje
        }
    }

    private fun onClick() {
        finish()
    }
}