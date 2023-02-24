package com.example.crogue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.util.*

class Register : AppCompatActivity() {
    lateinit var correoEt : EditText
    lateinit var passEt :EditText
    lateinit var nombreEt :EditText
    lateinit var fechaTxt : TextView
    lateinit var Registrar : Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //Init vars
        correoEt =findViewById<EditText>(R.id.correoEt)
        passEt =findViewById<EditText>(R.id.passEt)
        nombreEt =findViewById<EditText>(R.id.nombreEt)
        fechaTxt =findViewById<TextView>(R.id.fechaEt)
        Registrar =findViewById<Button>(R.id.Registrar)
        //Date
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance() //or use
        getDateInstance()
        val formatedDate = formatter.format(date)
        //Put date into textview
        fechaTxt.setText(formatedDate)

        //Init DB
        auth = FirebaseAuth.getInstance()
        Registrar.setOnClickListener(){
            //Abans de fer el registre validem les dades
            var email:String = correoEt.getText().toString()
            var pass:String = passEt.getText().toString()
            // validació del correu
            // si no es de tipus correu
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                correoEt.setError("Invalid Mail")
            }
            else if (pass.length<6) {
                passEt.setError("Password less than 6 chars")
            }
            else
            {
                RegistrarJugador(email, pass)
            }
        }

    }
    fun RegistrarJugador(email:String, passw:String){
        auth.createUserWithEmailAndPassword(email, passw)
            .addOnCompleteListener(this) { task ->
                34
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in  user's information
                    Toast.makeText(
                        this,"createUserWithEmail:success",Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

            }
    }
    fun updateUI(user: FirebaseUser?){
        //hi ha un interrogant perquè podria ser null
        if (user!=null)
        {
            var puntuacio: Int = 0
            var uidString: String = user.uid
            var correoString: String = correoEt.getText().toString()
            var passString: String = passEt.getText().toString()
            var nombreString: String = nombreEt.getText().toString()
            var fechaString: String= fechaTxt.getText().toString()
            var dadesJugador : HashMap<String,String> = HashMap<String, String>()
            dadesJugador.put ("Uid",uidString)
            dadesJugador.put ("Email",correoString)
            dadesJugador.put ("Password",passString)
            dadesJugador.put ("Nom",nombreString)
            dadesJugador.put ("Data",fechaString)
            dadesJugador.put ("Puntuacio",puntuacio.toString())
            var database: FirebaseDatabase =
                FirebaseDatabase.getInstance("https://crogue-357e6-default-rtdb.europe-west1.firebasedatabase.app/")
            var reference: DatabaseReference = database.getReference("Player DB")
            if(reference!=null) {
                //crea un fill amb els valors de dadesJugador
                reference.child(uidString).setValue(dadesJugador)
                Toast.makeText(
                    this, "USUARI BEN REGISTRAT",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, Register::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "ERROR BD", Toast.LENGTH_SHORT).show()
            }

        }
        else
        {
            Toast.makeText( this,"ERROR CREATE USER",Toast.LENGTH_SHORT).show()
        }
        finish()
    }

}
