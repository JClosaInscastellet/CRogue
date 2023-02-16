package com.example.crogue

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginbtn = findViewById<Button>(R.id.BTMLOGIN)
        val registerbtn = findViewById<Button>(R.id.BTMREGISTRO)

        loginbtn.setOnClickListener(){
            Toast.makeText(this, "click botó " +
                    "login",Toast.LENGTH_LONG).show();
        }

        registerbtn.setOnClickListener(){
            Toast.makeText(this, "click botó " +
                    "register",Toast.LENGTH_LONG).show();
        }
    }

}