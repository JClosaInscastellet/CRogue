package com.example.crogue

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startscreen)
        val loginbtn = findViewById<Button>(R.id.BTMLOGIN)
        val registerbtn = findViewById<Button>(R.id.BTMREGISTRO)

        loginbtn.setOnClickListener(){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        registerbtn.setOnClickListener(){
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

}