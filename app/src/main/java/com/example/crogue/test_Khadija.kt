package com.example.crogue


import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class test_Khadija : AppCompatActivity(), GestureDetector.OnGestureListener {
    lateinit var tv: TextView
    var secondsLeft = 0
    lateinit var mainHandler: Handler
    var puntuation = 0;
    var maxHp = 20;
    var hp = 20;


    // Declaring gesture detector, swipe threshold, and swipe velocity threshold
    private lateinit var gestureDetector: GestureDetector
    private val swipeThreshold = 100
    private val swipeVelocityThreshold = 100

    var cols = 29;
    var rows = 44;
    val first = Array(cols) { CharArray(rows) }
    var numberOfRooms = 5;

    val enemys = ArrayList<MutableMap<String, Int>>(numberOfRooms - 1)

    var playerX = 0
    var playerY = 0
    var playerAtk = 3
    var log = ""
    var exitX = 0
    var exitY = 0
    var killedEnemys = 0

    // Override this method to recognize touch event
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    // All the below methods are GestureDetector.OnGestureListener members
    // Except onFling, all must "return false" if Boolean return type
    // and "return" if no return type
    override fun onDown(e: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent) {
        return
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(
        e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {
        return
    }

    override fun onFling(
        e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float
    ): Boolean {
        try {
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x
            val angle = Math.toDegrees(
                Math.atan2(
                    (e1.y - e2.y).toDouble(), (e2.x - e1.x).toDouble()
                )
            ).toFloat()
            if (angle < -45 && angle >= -135) {

                if (first[playerX + 1][playerY] == '#') {

                } else if (first[playerX + 1][playerY] == 'E') {
                    recreate()
                    //finish()
                    //startActivity(intent)
                } else if (first[playerX + 1][playerY] == 'U') {
                    for (i in 0..enemys.size - 1) {
                        //comprovar up del enemigo
                        if (first[enemys[i].get("x")!! - 1][enemys[i].get("y")!!] == '@') {
                            //restar vidas al jugador
                            hp -= enemys[i].get("atk")!!
                            //restar vidas al enemigo
                            enemys[i].set("hp", enemys[i].get("hp")!! - playerAtk)
                            if (enemys[i].get("hp")!! <= 0) {
                                first[enemys[i].get("x")!!][enemys[i].get("y")!!] = '·'
                                puntuation += 10
                                log = "You killed an undead"
                                enemys.removeAt(i)
                                killedEnemys += 1
                                if (killedEnemys >= 5) {
                                    hp += 2
                                    maxHp += 2
                                    playerAtk += 1
                                }
                            }

                        }
                    }

                } else {
                    if (first[playerX + 1][playerY] == '*') {
                        puntuation += 5;
                        first[playerX + 1][playerY] = '@'
                        first[playerX][playerY] = '·'
                        playerX = playerX + 1;
                    } else {
                        first[playerX + 1][playerY] = '@'
                        first[playerX][playerY] = '·'
                        playerX = playerX + 1;
                    }
                }


            }

            if (angle > 45 && angle <= 135) {

                if (first[playerX - 1][playerY] == '#') {

                } else if (first[playerX - 1][playerY] == 'E') {
                    recreate()
                    //finish()
                    //startActivity(intent)
                } else if (first[playerX - 1][playerY] == 'U') {
                    for (i in 0..enemys.size - 1) {
                        //comprovar down del enemigo
                        if (first[enemys[i].get("x")!! + 1][enemys[i].get("y")!!] == '@') {
                            //restar vidas al jugador
                            hp -= enemys[i].get("atk")!!
                            //restar vidas al enemigo
                            enemys[i].set("hp", enemys[i].get("hp")!! - playerAtk)
                            if (enemys[i].get("hp")!! <= 0) {
                                first[enemys[i].get("x")!!][enemys[i].get("y")!!] = '·'
                                puntuation += 10
                                log = "You killed an undead"
                                enemys.removeAt(i)
                                killedEnemys += 1
                                if (killedEnemys >= 5) {
                                    hp += 2
                                    maxHp += 2
                                    playerAtk += 1
                                }
                            }

                        }
                    }

                } else {
                    if (first[playerX - 1][playerY] == '*') {
                        puntuation += 5;
                        first[playerX - 1][playerY] = '@'
                        first[playerX][playerY] = '·'
                        playerX = playerX - 1;
                    } else {
                        first[playerX - 1][playerY] = '@'
                        first[playerX][playerY] = '·'
                        playerX = playerX - 1;
                    }

                }


            }
            if (angle > -45 && angle <= 45) {

                if (first[playerX][playerY + 1] == '#') {

                } else if (first[playerX][playerY + 1] == 'E') {
                    recreate()
                    //finish()
                    //startActivity(intent)
                } else if (first[playerX][playerY - 1] == 'U') {
                    for (i in 0..enemys.size + 1) {
                        //comprovar la derecha del enemigo
                        if (first[enemys[i].get("x")!!][enemys[i].get("y")!! - 1] == '@') {
                            //restar vidas al jugador
                            hp -= enemys[i].get("atk")!!
                            //restar vidas al enemigo
                            enemys[i].set("hp", enemys[i].get("hp")!! - playerAtk)
                            if (enemys[i].get("hp")!! <= 0) {
                                first[enemys[i].get("x")!!][enemys[i].get("y")!!] = '·'
                                puntuation += 10
                                log = "You killed an undead"
                                enemys.removeAt(i)
                                killedEnemys += 1
                                if (killedEnemys >= 5) {
                                    hp += 2
                                    maxHp += 2
                                    playerAtk += 1
                                }
                            }

                        }
                    }
                } else {
                    if (first[playerX][playerY + 1] == '*') {
                        puntuation += 5;
                        first[playerX][playerY + 1] = '@'
                        first[playerX][playerY] = '·'
                        playerY = playerY + 1;
                    } else {

                        first[playerX][playerY + 1] = '@'
                        first[playerX][playerY] = '·'
                        playerY = playerY + 1;
                    }

                }

            }

            if (angle >= 135 && angle < 180 || angle < -135 && angle > -180) {

                if (first[playerX][playerY - 1] == '#') {


                } else if (first[playerX][playerY - 1] == 'E') {
                    recreate()
                    //finish()
                    //startActivity(intent)
                } else if (first[playerX][playerY - 1] == 'U') {
                    for (i in 0..enemys.size - 1) {
                        //comprovar la derecha del enemigo
                        if (first[enemys[i].get("x")!!][enemys[i].get("y")!! + 1] == '@') {
                            //restar vidas al jugador
                            hp -= enemys[i].get("atk")!!
                            //restar vidas al enemigo
                            enemys[i].set("hp", enemys[i].get("hp")!! - playerAtk)
                            if (enemys[i].get("hp")!! <= 0) {
                                first[enemys[i].get("x")!!][enemys[i].get("y")!!] = '·'
                                puntuation += 10
                                log = "You killed an undead"
                                enemys.removeAt(i)
                                killedEnemys += 1
                                if (killedEnemys >= 5) {
                                    hp += 2
                                    maxHp += 2
                                    playerAtk += 1
                                }
                            }

                        }
                    }
                } else {
                    if (first[playerX][playerY - 1] == '*') {
                        puntuation += 5;
                        first[playerX][playerY - 1] = '@'
                        first[playerX][playerY] = '·'
                        playerY = playerY - 1;
                    } else {
                        first[playerX][playerY - 1] = '@'
                        first[playerX][playerY] = '·'
                        playerY = playerY - 1;
                    }

                }

            }
            for (i in 0..enemys.size - 1) {
                var direction = Random.nextInt(0, 4)
                if (first[enemys[i].get("x")!!][enemys[i].get("y")!! - 1] == '@' ||
                    first[enemys[i].get("x")!!][enemys[i].get("y")!! + 1] == '@' ||
                    first[enemys[i].get("x")!! - 1][enemys[i].get("y")!!] == '@' ||
                    first[enemys[i].get("x")!! + 1][enemys[i].get("y")!!] == '@'
                ) {

                } else {
                    if (direction == 0) {
                        if (first[enemys[i].get("x")!!][enemys[i].get("y")!! - 1] == '·') {
                            first[enemys[i].get("x")!!][enemys[i].get("y")!! - 1] = 'U'
                            first[enemys[i].get("x")!!][enemys[i].get("y")!!] = '·'
                            enemys[i].set("y", enemys[i].get("y")!! - 1)
                        }
                    } else if (direction == 1) {
                        if (first[enemys[i].get("x")!!][enemys[i].get("y")!! + 1] == '·') {
                            first[enemys[i].get("x")!!][enemys[i].get("y")!! + 1] = 'U'
                            first[enemys[i].get("x")!!][enemys[i].get("y")!!] = '·'
                            enemys[i].set("y", enemys[i].get("y")!! + 1)
                        }
                    } else if (direction == 2) {
                        if (first[enemys[i].get("x")!! - 1][enemys[i].get("y")!!] == '·') {
                            first[enemys[i].get("x")!! - 1][enemys[i].get("y")!!] = 'U'
                            first[enemys[i].get("x")!!][enemys[i].get("y")!!] = '·'
                            enemys[i].set("x", enemys[i].get("x")!! - 1)
                        }
                    } else if (direction == 3) {
                        if (first[enemys[i].get("x")!! + 1][enemys[i].get("y")!!] == '·') {
                            first[enemys[i].get("x")!! + 1][enemys[i].get("y")!!] = 'U'
                            first[enemys[i].get("x")!!][enemys[i].get("y")!!] = '·'
                            enemys[i].set("x", enemys[i].get("x")!! + 1)
                        }
                    }
                }
            }

        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return true
    }


    //---------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_khadija)
        tv = findViewById(R.id.textView)

        // Initializing the gesture detector
        gestureDetector = GestureDetector(this)

        //Generar nivell

        for (i in 0..cols - 1) {
            for (j in 0..rows - 1) {
                first[i][j] = '#'
            }
        }
        //-------------------------
        val rooms = ArrayList<MutableMap<String, Int>>(numberOfRooms)
        /*rooms.add(mutableMapOf<String,Int>(
            "x" to 4,
            "y" to 4,
            "width" to 4,
            "heigth" to 4
            ))*/

        var valid = false

        for (i in 0..numberOfRooms - 1) {
            rooms.add(
                mutableMapOf<String, Int>(
                    "y" to Random.nextInt(1, rows - 6),
                    "x" to Random.nextInt(1, cols - 6),
                    "width" to Random.nextInt(3, 5),
                    "height" to Random.nextInt(4, 7)
                )
            )
            valid = false
            while (!valid) {
                if (first[rooms[i].get("x")!!][rooms[i].get("y")!!] == '·' || first[rooms[i].get("x")!! + rooms[i].get(
                        "width"
                    )!!][rooms[i].get("y")!!] == '·' || first[rooms[i].get("x")!!][rooms[i].get("y")!! + rooms[i].get(
                        "height"
                    )!!] == '·' || first[rooms[i].get("x")!! + rooms[i].get("width")!!][rooms[i].get(
                        "y"
                    )!! + rooms[i].get(
                        "height"
                    )!!] == '·'
                ) {
                    rooms[i] = (mutableMapOf<String, Int>(
                        "y" to Random.nextInt(1, rows - 6),
                        "x" to Random.nextInt(1, cols - 6),
                        "width" to Random.nextInt(3, 5),
                        "height" to Random.nextInt(4, 7)
                    ))
                } else {
                    valid = true
                }
            }
            for (j in rooms[i].get("x")!!..rooms[i].get("x")!! + rooms[i].get("width")!!) {
                for (k in rooms[i].get("y")!!..rooms[i].get("y")!! + rooms[i].get("height")!!) {
                    first[j][k] = '·'
                }
            }
        }

        for (i in 0..numberOfRooms - 1) {
            if (i < numberOfRooms - 1) {
                val x = rooms[i].get("x")!! + rooms[i].get("width")!! / 2;
                val y = rooms[i].get("y")!! + rooms[i].get("height")!! / 2;
                val x2 = rooms[i + 1].get("x")!! + rooms[i + 1].get("width")!! / 2;
                val y2 = rooms[i + 1].get("y")!! + rooms[i + 1].get("height")!! / 2;

                val startpath = x - (x - x2)
                val endpath = y - (y - y2)

                for (j in startpath + 1..x) {
                    first[j][y] = '·'
                }
                for (j in x..startpath) {
                    first[j][y] = '·'
                }
                for (j in endpath + 1..y) {
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
        playerX = rooms[0].get("x")!! + rooms[0].get("width")!! / 2;
        playerY = rooms[0].get("y")!! + rooms[0].get("height")!! / 2;

        first[playerX][playerY] = '@'
        exitX = rooms[4].get("x")!! + rooms[4].get("width")!! / 2;
        exitY = rooms[4].get("y")!! + rooms[4].get("height")!! / 2;
        first[exitX][exitY] = 'E'
        // Generar Enemics --------

        for (i in 1..numberOfRooms - 2) {
            val x = rooms[i].get("x")!! + rooms[i].get("width")!! / 2;
            val y = rooms[i].get("y")!! + rooms[i].get("height")!! / 2;
            enemys.add(
                mutableMapOf<String, Int>(
                    "x" to x,
                    "y" to y,
                    "atk" to 3,
                    "hp" to 5
                )
            )

            first[x][y] = 'U'

        }
        //----------------
        for (i in 0..5) {
            var valid = false
            var randX: Int;
            var randY: Int;

            while (!valid) {
                randX = Random.nextInt(1, cols - 1)
                randY = Random.nextInt(1, rows - 1)
                if (first[randX][randY] == '·') {
                    valid = true;
                    first[randX][randY] = '*'
                } else {
                    randX = Random.nextInt(1, cols - 1)
                    randY = Random.nextInt(1, rows - 1)
                }
            }

        }

        handler.postDelayed(object : Runnable {
            override fun run() {
                tv.text = ""

                //tv.text = first.contentToString()
                for (i in 0..cols - 1) {
                    tv.setText(tv.text.toString() + String(first[i]))
                }
                tv.setText(
                    tv.text.toString() + "\nP: " + puntuation.toString() +
                            "\t   HP: " + hp.toString() + "/" + maxHp.toString() +
                            "\t   ATK: " + playerAtk.toString() + "\n" + log
                )
                handler.postDelayed(this, 100)//1 sec delay
            }
        }, 0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("puntuation", puntuation)
        outState.putInt("maxHp", maxHp)
        outState.putInt("playerAtk", playerAtk)
        outState.putInt("hp", hp)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        puntuation = savedInstanceState.getInt("puntuation")
        maxHp = savedInstanceState.getInt("maxHp")
        playerAtk = savedInstanceState.getInt("playerAtk")
        hp = savedInstanceState.getInt("hp")
        Toast.makeText(applicationContext, "puntuation: " + puntuation.toString(), Toast.LENGTH_LONG).show()

    }
}