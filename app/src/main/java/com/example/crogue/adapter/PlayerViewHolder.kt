package com.example.crogue.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.crogue.Player
import com.example.crogue.R
import com.squareup.picasso.Picasso

class PlayerViewHolder (view: View): RecyclerView.ViewHolder(view) {
    //afegim les variables que apunten als continguts del layout
    val nomJugador=view.findViewById<TextView>(R.id.tvNom_Jugador)
    val puntuacioJugador=view.findViewById<TextView>(R.id.tvPuntuacio_Jugador)
    val foto=view.findViewById<ImageView>(R.id.ivJugador)

    fun render(jugadorModel: Player){
        //la cridara per a cada jugador
        nomJugador.text=jugadorModel.nom_jugador
        puntuacioJugador.text=jugadorModel.puntuacio.toString() //recordem que és un int
        //utilitzant picasso: https://rubentejera.com/picasso-libreria-gestion-imagenes-para-android/
        Picasso.get().load(jugadorModel.foto).resize(150,150).into(foto)
        //aqui agafarem quan pica sobre una imatge
        foto.setOnClickListener(){
            Toast.makeText(foto.context, nomJugador.text, Toast.LENGTH_LONG).show()
        }
    }
}