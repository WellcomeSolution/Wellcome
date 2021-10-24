package com.example.wellcome

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
//import com.example.wellcome.model.User

//import base de donne
class AddUserActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //créer l'objet de BD
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adduser)
        var add_nom: EditText = findViewById(R.id.editnom)
        var add_prenom: EditText = findViewById(R.id.editprenom)
        var add_adresse: EditText = findViewById(R.id.editaddress)
        var add_email: EditText = findViewById(R.id.editemail)
        var add_phone: EditText = findViewById(R.id.editphone)
        var btnretour: Button = findViewById(R.id.ret)
        var btnenregistre: Button = findViewById(R.id.RegisterBtnF)
        btnretour.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btnenregistre.setOnClickListener {
            var nom: String = add_nom.text.toString()
            var prenom: String = add_prenom.text.toString()
            var adresse: String = add_adresse.text.toString()
            var email: String = add_email.text.toString()
            var phone: String = add_phone.text.toString()
            var number: Int = phone.toInt()
            //var user = User(nom,prenom,adresse,email,number)
            //db.insert(user)
        }
    }


}