package com.example.crogue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.Timer
import kotlin.concurrent.schedule
import android.media.MediaPlayer

class Splash : AppCompatActivity() {

    val SPLASH_DURATION: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FullScreen
        supportActionBar?.hide()

        //Get ImageView
        val splashIMView = findViewById<ImageView>(R.id.splashIM)
        //var para imortar sonido spalsh
        val mediaPlayer = MediaPlayer.create(this, R.raw.opening);
        mediaPlayer.setVolume(100f , 100f);

        //Put Splash into it
        Glide.with(this).load(R.drawable.crouge_logo).into(splashIMView)

        //Make it last 3 sec
        Timer().schedule(SPLASH_DURATION){
            mediaPlayer.start()
            changeActivity()
        }

    }
    fun changeActivity(){
        //Change activiry
        val intent = Intent(this, list_players::class.java)
        startActivity(intent)
    }
}