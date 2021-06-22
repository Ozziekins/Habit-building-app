package com.example.drafttwo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        loginButton.setOnClickListener {
            val email = emailLogin.text.toString()
            val password = passwordLogin.text.toString()
            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful) {
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                }
                if(!it.isSuccessful) return@addOnCompleteListener

            }
                .addOnFailureListener{
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()

                }
        }
        backToRegisterText.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}