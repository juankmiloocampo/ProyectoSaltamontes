package com.jumi.practica1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class calcularPromedio : AppCompatActivity() {

    val registroEstudiante: MutableMap<String, Any> = mutableMapOf<String, Any>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_promedio)
        iniciar()
    }

    private fun iniciar() {
        val miBoton: Button = findViewById(R.id.btnCalcular)
        miBoton.setOnClickListener{onClick()}

        val miBoton2: Button = findViewById(R.id.btnVolver)
        miBoton2.setOnClickListener{salir()}
    }

    private fun onClick() {
        var campoTexto1: EditText = findViewById(R.id.nombres)
        val nombre: String = campoTexto1.text.toString()

        var campoTexto2: EditText  = findViewById(R.id.documento)
        val documento: String = campoTexto2.text.toString()

        var campoTexto3: EditText = findViewById(R.id.direccion)
        val direccion: String = campoTexto3.text.toString()

        var campoTexto4: EditText  = findViewById(R.id.edad)
        val edad: String = campoTexto4.text.toString()

        var campoTexto5: EditText  = findViewById(R.id.telefono)
        val telefono: String = campoTexto5.text.toString()

        var spinner1: Spinner = findViewById(R.id.materia1)
        val materia1: String = spinner1.selectedItem.toString()

        var campoTexto6: EditText  = findViewById(R.id.nota1)
        val nota1: String = campoTexto6.text.toString()

        spinner1 = findViewById(R.id.materia2)
        val materia2: String = spinner1.selectedItem.toString()

        var campoTexto7: EditText  = findViewById(R.id.nota2)
        val nota2: String = campoTexto7.text.toString()

        spinner1 = findViewById(R.id.materia3)
        val materia3: String = spinner1.selectedItem.toString()

        var campoTexto8: EditText  = findViewById(R.id.nota3)
        val nota3: String = campoTexto8.text.toString()

        spinner1 = findViewById(R.id.materia4)
        val materia4: String = spinner1.selectedItem.toString()

        var campoTexto9: EditText  = findViewById(R.id.nota4)
        val nota4: String = campoTexto9.text.toString()

        spinner1 = findViewById(R.id.materia5)
        val materia5: String = spinner1.selectedItem.toString()

        var campoTexto10: EditText  = findViewById(R.id.nota5)
        val nota5: String = campoTexto10.text.toString()

        val mensajeBuilder = StringBuilder()
        mensajeBuilder.append("Los siguientes campos deben tener valores numericos en un rango entre 0 y 5")
        var continuar = true

        val listaNotas = listOf<String>(nota1, nota2, nota3, nota4, nota5)

        var comprobarCamposTexto: Array<EditText> = arrayOf<EditText>(campoTexto1, campoTexto2, campoTexto3, campoTexto4, campoTexto5)

        for (comprobacion in comprobarCamposTexto) {
            if (comprobacion.length() <= 0){
                Toast.makeText(this, "Por favor asegurese de llenar todos los campos de informacion\n", Toast.LENGTH_LONG).show()
                continuar = false
                break
            }
            else {
                continuar = true
            }

        }

        if(continuar){

            for(i in 0 .. listaNotas.size - 1) {
                val nota = listaNotas[i]
                if (!validarEntradaNota(nota)){
                    mensajeBuilder.append("\n - Nota ${i+1}")
                    continuar = false
                }
            }

            if(continuar){
                val promedio: Double = calcular(nota1.toDouble(), nota2.toDouble(), nota3.toDouble(), nota4.toDouble(), nota5.toDouble())
                val gana : Boolean = validarPromedio(promedio)
                var recupera = true
                if(!gana) recupera = validarRecuperacion(promedio)
                var lista: ArrayList<String> = arrayListOf<String>(documento, nombre, direccion, edad, telefono, materia1, nota1, materia2, nota2, materia3, nota3, materia4, nota4, materia5, nota5, gana.toString(), recupera.toString(), promedio.toString())
                registroEstudiante(documento, lista)
                datosEstadisticas()
                val intent = Intent(this, ImprimirDatosRegistro ::class.java)
                val miBundle: Bundle = Bundle()
                miBundle.putStringArrayList("abc", lista)
                intent.putExtras(miBundle)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "$mensajeBuilder", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun datosEstadisticas() {
        var listaEstadisticas = arrayListOf<Int>()
        var cantidadUsuarios: Int = registroEstudiante.size
        var cantidadGanan = 0
        var cantidadRecuperan = 0
        var cantidadPierden = 0
        for (persona in registroEstudiante) {
            var valor: MutableList<Any> = persona.value as MutableList<Any>
            if(valor[15]=="true")
            {
                cantidadGanan+=1
            }
            else {
                cantidadPierden += 1
                if (valor[16] == "true") {
                    cantidadRecuperan += 1
                }
            }
        }
        listaEstadisticas.add(cantidadUsuarios)
        listaEstadisticas.add(cantidadGanan)
        listaEstadisticas.add(cantidadPierden)
        listaEstadisticas.add(cantidadRecuperan)

        DataManager.get().datosEstadistica = listaEstadisticas
    }

    private fun registroEstudiante(documento: String, lista: List<String>) {
        if(registroEstudiante.isEmpty())
        {
            registroEstudiante[documento] = lista
        }

        var bandera = false
        for(documentoR in registroEstudiante.keys){
            if(documentoR == documento)
            {
                bandera = true
            }
        }
        if(bandera == true)
        {
            var mensaje = "El usuario con el documento: $documento ya se encuentra registrado\n- Pero sus datos han sido actualizados"
            Toast.makeText(this, "$mensaje", Toast.LENGTH_LONG).show()
            registroEstudiante[documento] = lista
        }
        else{
            registroEstudiante[documento] = lista
            Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_LONG).show()
        }
    }

    private fun validarRecuperacion(promedio: Double): Boolean {
        if(promedio > 2.5) return true
        return false
    }

    private fun validarPromedio(promedio: Double): Boolean {
        if(promedio > 3.5) return true
        return false
    }

    private fun validarEntradaNota(nota: String): Boolean {
        if (nota == null || nota.isEmpty())
            return false
        else if(!notaValida(nota.toDouble())){
            return false
        }
        return true
    }

    private fun notaValida(nota: Double): Boolean {
        return nota in 0.0 .. 5.0
    }

    private fun calcular(nota1: Double, nota2: Double, nota3: Double, nota4: Double, nota5: Double): Double {
        var nota: Double = 0.0
        nota = (nota1 + nota2 + nota3 + nota4 + nota5)/5
        return nota
    }

    private fun validarTexto(notaEditText: EditText): Boolean {
        var campo: String = notaEditText.text.toString()

        if (campo.length >= 1){
            return true
        }
        return false
    }

    private fun salir() {
        finish()
    }
}