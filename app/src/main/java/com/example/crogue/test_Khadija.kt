package com.example.crogue


import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.ArrayList
import kotlin.random.Random


class test_Khadija : AppCompatActivity() {
    lateinit var tv: TextView
    var secondsLeft = 0
    lateinit var mainHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_khadija)
        tv = findViewById(R.id.textView)

        //Generar nivell
        var cols = 30;
        var rows = 44;
        var numberOfRooms = 5;
        val first = Array(cols) { CharArray(rows) }
        for (i in 0..cols-1) {
            for (j in 0..rows-1) {
                first[i][j] = '#'
            }
        }
        //-------------------------
        val rooms = ArrayList<MutableMap<String,Int>>(numberOfRooms)
        /*rooms.add(mutableMapOf<String,Int>(
            "x" to 4,
            "y" to 4,
            "width" to 4,
            "heigth" to 4
            ))*/

        var valid = false

        for (i in 0..numberOfRooms-1)
        {
            rooms.add(mutableMapOf<String,Int>(
                "y" to Random.nextInt(1,rows-6),
                "x" to Random.nextInt(1,cols-6),
                "width" to Random.nextInt(3,5),
                "height" to Random.nextInt(4,7)))
            valid = false
            while (!valid){
                if (first[rooms[i].get("x")!!] [rooms[i].get("y")!!] == '·' ||
                    first[rooms[i].get("x")!!+rooms[i].get("width")!!] [rooms[i].get("y")!!] == '·' ||
                    first[rooms[i].get("x")!!] [rooms[i].get("y")!!+rooms[i].get("height")!!] == '·' ||
                    first[rooms[i].get("x")!!+rooms[i].get("width")!!] [rooms[i].get("y")!!+rooms[i].get("height")!!] == '·'
                ){
                    rooms[i] =(mutableMapOf<String,Int>(
                        "y" to Random.nextInt(1,rows-6),
                        "x" to Random.nextInt(1,cols-6),
                        "width" to Random.nextInt(3,5),
                        "height" to Random.nextInt(4,7)))
                }else{
                    valid = true
                }
            }
            for (j in rooms[i].get("x")!!..rooms[i].get("x")!!+rooms[i].get("width")!!) {
                for (k in rooms[i].get("y")!!..rooms[i].get("y")!!+rooms[i].get("height")!!) {
                    first[j][k] = '·'
                }
            }
        }

        for (i in 0..numberOfRooms-1){
            if(i < numberOfRooms -1){
                val x = rooms[i].get("x")!! + rooms[i].get("width")!! / 2;
                val y = rooms[i].get("y")!! + rooms[i].get("height")!! / 2;
                val x2 = rooms[i+1].get("x")!! + rooms[i+1].get("width")!! / 2;
                val y2 = rooms[i+1].get("y")!! + rooms[i+1].get("height")!! / 2;

                val startpath = x - (x-x2)
                val endpath = y - (y-y2)

                for (j in startpath+1..x) {
                    first[j][y] = '·'
                }
                for (j in x..startpath) {
                    first[j][y] = '·'
                }
                for (j in endpath+1..y) {
                    first[x2][j] = '·'
                }
                for (j in y..endpath) {
                    first[x2][j] = '·'
                }

            }
        }



        val handler = Handler()
        //IMPORTAR font
        val tf2 = Typeface.createFromAsset(assets, "fonts/SpaceMono-Regular.ttf")
        tv.setTypeface(tf2) //cambiar font
        val playerX = rooms[0].get("x")!! + rooms[0].get("width")!! / 2;
        val playerY = rooms[0].get("y")!! + rooms[0].get("height")!! / 2;
        first[playerX][playerY] = '@'
        val exitX = rooms[4].get("x")!! + rooms[4].get("width")!! / 2;
        val exitY = rooms[4].get("y")!! + rooms[4].get("height")!! / 2;
        first[exitX][exitY] = 'E'

        handler.postDelayed(object : Runnable {
            override fun run() {
                tv.text = ""

                //tv.text = first.contentToString()
                for (i in 0..cols-1) {
                    tv.setText(tv.text.toString() + String(first[i]))
                }

                handler.postDelayed(this, 50)//1 sec delay
            }
        }, 0)
    }


}