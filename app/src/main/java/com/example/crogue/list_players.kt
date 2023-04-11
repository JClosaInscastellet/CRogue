package com.example.crogue

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crogue.adapter.PlayerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class list_players : AppCompatActivity() {

    val players = mutableListOf<Player>()

    val images = listOf < String >(
        "https://www.kasandbox.org/programming-images/avatars/piceratops-tree.png",
        "https://www.kasandbox.org/programming-images/avatars/leafers-seed.png",
        "https://www.kasandbox.org/programming-images/avatars/leaf-yellow.png",
        "https://www.kasandbox.org/programming-images/avatars/leaf-blue.png"
    )

    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null;
    var cont = 0
    var image = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lplayers)

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        getUsers()

    }

    fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerOne)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlayerAdapter(players)
    }

    private fun getUsers() {

        var database: FirebaseDatabase = FirebaseDatabase.getInstance("https://crogue-357e6-default-rtdb.europe-west1.firebasedatabase.app/")
        var bdreference: DatabaseReference = database.getReference("Player DB")


        bdreference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (ds in snapshot.getChildren()) {
                    val name = ds.child("Nom").getValue().toString()
                    val score = ds.child("Puntuacio").getValue().toString()

                    getImage() //obtener imágenes por orden
                    val newPlayer =Player(nom_jugador = name, puntuacio = score.toInt(), foto = image)
                    players.add(newPlayer) //añadir a lista de jugadores

                }

                initRecyclerView() //una vez la lista de jugadores ya está completa se llama a la recycleview
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("pepe", "ERROR DATABASE CANCEL")
            }
        })
    }

    private fun getImage() {
        image = images[cont]
        //Log.d("kk", "**imagen " + image + " contador" + cont)
        if (cont >= images.size-1) {
            cont = 0
        } else {
            cont += 1
        }
    }

}


