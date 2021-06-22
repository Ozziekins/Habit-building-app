package com.example.drafttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.ktx.*
import com.google.firebase.firestore.FirebaseFirestore
import com.example.drafttwo.GoogleSignInActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val username = usernameRegister.text.toString()
            val email = emailRegister.text.toString()
            val password = passwordRegister.text.toString()
           val data = hashMapOf(
                "Username" to username,
                    "Email" to email,
                    "Password" to password
            )
            if(email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if(!it.isSuccessful) {
                    return@addOnCompleteListener
                }
                if(it.isSuccessful){
                    val db = FirebaseFirestore.getInstance()
                    val auth = FirebaseAuth.getInstance()
                    db.collection("Users").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).set(data)
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                }

            }
                .addOnFailureListener{
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()

                }
        }

        signInGoogle.setOnClickListener{

            val intent = Intent(this, GoogleSignInActivity::class.java)
            startActivity(intent)

        }

        alreadyHaveAccount.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}