package com.example.crogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crogue.Player
import com.example.crogue.R

class PlayerAdapter (val players:List<Player>): RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        //infla el layout i crida al Jugadors View Holder
        val layoutInflater= LayoutInflater.from(parent.context)
        return PlayerViewHolder(layoutInflater.inflate(R.layout.item_player, parent, false))
    }

    override fun getItemCount(): Int {
        //retorna el tamany del la taula
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        //aquest mètode és que va passant per cada un dels items i crida al render
        val item=players[position]
        holder.render(item)
    }
}