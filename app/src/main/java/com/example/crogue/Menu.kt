package com.example.crogue

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class Menu : AppCompatActivity() {

    //creem unes variables per comprovar ususari i authentificació
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null;

    lateinit var tancarSessio: Button
    lateinit var CreditsBtn: Button
    lateinit var PuntuacionsBtn: Button
    lateinit var jugarBtn: Button

    lateinit var puntuaciotxt: TextView
    lateinit var puntuacio: TextView
    lateinit var uid: TextView
    lateinit var correo: TextView
    lateinit var nom: TextView


    //reference serà el punter que ens envia a la base de dades de jugadors
    lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        auth= FirebaseAuth.getInstance()
        user =auth.currentUser

        //Aquí creem un tipus de lletra a partir de una font
        val tf = Typeface.createFromAsset(assets,"fonts/regular.ttf")
        val tf2 = Typeface.createFromAsset(assets,"fonts/secondfont.ttf")


        //buscar text per asignar-li tipus de lletra
        puntuaciotxt=findViewById(R.id.myScoretxt)

        puntuaciotxt.setTypeface(tf2)
        puntuaciotxt.setTypeface(tf)

        puntuacio=findViewById(R.id.scores)
        puntuacio.setTypeface(tf2)
        uid=findViewById(R.id.uid)
        uid.setTypeface(tf)
        correo=findViewById(R.id.email)
        correo.setTypeface(tf)
        nom=findViewById(R.id.name)
        nom.setTypeface(tf2)

        //asignar botones
        tancarSessio =findViewById<Button>(R.id.closeSessionBtn)
        tancarSessio.setTypeface(tf2) //cambiar tipus de font
        CreditsBtn =findViewById<Button>(R.id.CreditsBtn)
        CreditsBtn.setTypeface(tf2)
        PuntuacionsBtn =findViewById<Button>(R.id.scoresBtn)
        PuntuacionsBtn.setTypeface(tf2)
        jugarBtn =findViewById<Button>(R.id.playBtn)
        jugarBtn.setTypeface(tf2)

        tancarSessio.setOnClickListener(){
            tancalaSessio()
        }
        CreditsBtn.setOnClickListener(){
            Toast.makeText(this,"Credits", Toast.LENGTH_SHORT).show()
        }
        PuntuacionsBtn.setOnClickListener(){
            Toast.makeText(this,"Puntuacions", Toast.LENGTH_SHORT).show()
        }
        jugarBtn.setOnClickListener(){
            Toast.makeText(this,"JUGAR", Toast.LENGTH_SHORT).show()
        }

        consulta() //Aquesta funció busca a la base de dades les dades de l'usuari i les mostre per pantalla


    }
    //Ara farem un procediment tal que si el ususari esta logejat, tira un toast i continua, però si
    //no ho està t’envia a la mainActivity, on pots anar a crear usuari o logejar-te
    private fun Usuarilogejat()
    {
        if (user !=null)
        {
            Toast.makeText(this,"Jugador logejat",
                Toast.LENGTH_SHORT).show()
        }
        else
        {
            val intent= Intent(this, StartScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
    //Aquesta funció busca a la base de dades les dades de l'usuari i les mostre per pantalla
    private fun consulta(){
        var database: FirebaseDatabase = FirebaseDatabase.getInstance("https://crogue-357e6-default-rtdb.europe-west1.firebasedatabase.app/")
        var bdreference:DatabaseReference = database.getReference("Player DB")

        bdreference.addValueEventListener (object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.i ("DEBUG","arrel value"+ snapshot.getValue().toString())
                Log.i ("DEBUG","arrel key"+ snapshot.key.toString())

                // ara capturem tots els fills
                var trobat:Boolean =false
                for (ds in snapshot.getChildren()) {

                    Log.i ("DEBUG","DS key:"+ds.child("Uid").key.toString())
                    Log.i ("DEBUG","DS value:"+ds.child("Uid").getValue().toString())
                    Log.i ("DEBUG","DS data:"+ds.child("Data").getValue().toString())
                    Log.i ("DEBUG","DS mail:"+ds.child("Email").getValue().toString())
                    //mirem si el mail és el mateix que el del jugador
                    //si és així, mostrem les dades als textview corresponents
                    if (ds.child("Email").getValue().toString().equals(user?.email)){

                        trobat=true

                        //carrega els textview
                        puntuacio.setText(ds.child("Puntuacio").getValue().toString())
                        uid.setText(ds.child("Uid").getValue().toString())
                        correo.setText(ds.child("Email").getValue().toString())
                        nom.setText(ds.child("Nom").getValue().toString())
                    }
                    if (!trobat)
                    {
                        Log.e ("ERROR","ERROR NO TROBAT MAIL")
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e ("ERROR","ERROR DATABASE CANCEL")
            }
        })


    }
    private fun tancalaSessio() {
        auth.signOut() //tanca la sessió
        //va a la pantalla inicial
        val intent= Intent(this, StartScreen::class.java)
        startActivity(intent)
        finish()
    }


    override fun onStart() {
        Usuarilogejat()
        super.onStart()
    }

}