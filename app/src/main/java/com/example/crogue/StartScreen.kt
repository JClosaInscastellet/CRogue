package com.example.crogue

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class StartScreen : AppCompatActivity() {

    //Test per si c++ va
<<<<<<< HEAD
    /*companion object {
        private external fun return32():String
        init {
            System.loadLibrary("crogue")
        }
    }*/
=======

>>>>>>> main
    //per a comprovar si la sessió esta inicialitzada
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startscreen)

        //IMPORTAR font
        val tf2 = Typeface.createFromAsset(assets,"fonts/secondfont.ttf")

        //assigna valor a user
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        val loginbtn = findViewById<Button>(R.id.BTMLOGIN)
        loginbtn.setTypeface(tf2)
<<<<<<< HEAD
        //loginbtn.text = return32()
=======
>>>>>>> main
        val registerbtn = findViewById<Button>(R.id.BTMREGISTRO)
        registerbtn.setTypeface(tf2)

        loginbtn.setOnClickListener(){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        registerbtn.setOnClickListener(){
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    // Aquest mètode s'executarà quan s'obri el menu
    override fun onStart() {
        usuariLogejat()
        super.onStart()
    }
    private fun usuariLogejat() {
        if (user != null) {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }
    }


}
