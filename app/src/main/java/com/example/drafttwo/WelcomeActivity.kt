package com.example.drafttwo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_activity.*


class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        logout.setOnClickListener {
            val auth = FirebaseAuth.getInstance();
            auth.signOut();
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        addForm.setOnClickListener{
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }

}
