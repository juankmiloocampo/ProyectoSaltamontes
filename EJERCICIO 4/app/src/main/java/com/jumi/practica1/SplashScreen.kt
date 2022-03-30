package com.jumi.practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Handler().postDelayed(Runnable() {
            Toast.makeText(this, "HOLA", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        },3000)

    }
}