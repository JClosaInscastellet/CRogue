package com.example.crogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crogue.adapter.PlayerAdapter

class Lplayers : AppCompatActivity() {
    val players = listOf<Player>(
        Player("Pepe",12,"https://www.kasandbox.org/programming-images/avatars/piceratops-tree.png"),
        Player("Juanito",102,"https://www.kasandbox.org/programming-images/avatars/leafers-seed.png"),
        Player("Jaimito",18,"https://www.kasandbox.org/programming-images/avatars/leaf-yellow.png"),
        Player("Jorgito",98,"https://www.kasandbox.org/programming-images/avatars/leaf-blue.png")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lplayers)

        initRecyclerView()
    }
    fun initRecyclerView(){
        val recyclerView=findViewById<RecyclerView>(R.id.RecyclerOne)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= PlayerAdapter(players)
    }
}