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
    val players = listOf<Player>(
        Player("Pepe",12,"https://www.kasandbox.org/programming-images/avatars/piceratops-tree.png"),
        Player("Juanito",102,"https://www.kasandbox.org/programming-images/avatars/leafers-seed.png"),
        Player("Jaimito",18,"https://www.kasandbox.org/programming-images/avatars/leaf-yellow.png"),
        Player("Jorgito",98,"https://www.  kasandbox.org/programming-images/avatars/leaf-blue.png")
    )
    lateinit var reference: DatabaseReference
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null;
    var usersData= arrayListOf<String>() //array donde guardar la  de los usuarios
    var usersScore= arrayListOf<String>() //array donde guardar las puntuaciones



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lplayers)

        auth= FirebaseAuth.getInstance()
        user =auth.currentUser
        // getting ImageView by its id
        //initRecyclerView()
        getUsers()
    }
    fun initRecyclerView(){
        val recyclerView=findViewById<RecyclerView>(R.id.RecyclerOne)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=PlayerAdapter(players)
    }
    private fun getUsers(){

        var database: FirebaseDatabase = FirebaseDatabase.getInstance("https://crogue-357e6-default-rtdb.europe-west1.firebasedatabase.app/")
        var bdreference: DatabaseReference = database.getReference("Player DB")
        val getImage: DatabaseReference = bdreference.child("image")
        
        bdreference.addValueEventListener (object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //Log.i ("pepe","arrel value"+ snapshot.getValue().toString())
                //Log.i ("pepe","arrel key"+ snapshot.key.toString())


                for (ds in snapshot.getChildren()) {
                    val name = ds.child("Nom").getValue().toString()
                    val score = ds.child("Puntuacio").getValue().toString()
                    Log.d("pepe", name)
                    Log.d("pepe", score)
                    usersData.add(name)
                    usersData.add(score)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d ("pepe","ERROR DATABASE CANCEL")
            }
        })
    }

}


