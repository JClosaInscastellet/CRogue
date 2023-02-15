package com.example.crogue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.Timer
import kotlin.concurrent.schedule

class Splash : AppCompatActivity() {

    val SPLASH_DURATION: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FullScreen
        supportActionBar?.hide()

        //Get ImageView
        val splashIMView = findViewById<ImageView>(R.id.splashIM)

        //Put Splash into it
        Glide.with(this).load(R.drawable.splash).into(splashIMView)

        //Make it last 3 sec
        Timer().schedule(SPLASH_DURATION){
            changeActivity()
        }

    }
    fun changeActivity(){
        //Change activiry
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
}