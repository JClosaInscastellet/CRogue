package com.example.crogue


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.schedule


class test_Khadija : AppCompatActivity() {
    lateinit var tv: TextView
    var secondsLeft=0
    lateinit var mainHandler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_khadija)
        tv = findViewById(R.id.textView)

        val first: Array<Array<String>> = arrayOf(
            arrayOf("2","4", "6"),
            arrayOf("1", "3", "5")
        )
        var i = 0;
        val handler = Handler()

        handler.postDelayed(object : Runnable {
        override fun run() {
         for (i in first.indices)
            {
                var j = 0
                while (j < first[i].size) {
                    tv.text=tv.text.toString()+first[i][j].toString()
                    j++
                }
            }
                handler.postDelayed(this, 50)//1 sec delay
        }
        }, 0)
    }






}