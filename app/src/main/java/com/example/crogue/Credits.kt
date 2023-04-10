package com.example.crogue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Credits : AppCompatActivity() {
    lateinit var bt_menu: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
     //   bt_menu =findViewById<Button>(R.id.bt_manu)

      //  bt_menu.setOnClickListener(){
       //     val intent= Intent(this, Menu::class.java)
           // startActivity(intent)
      //  }
    }

}