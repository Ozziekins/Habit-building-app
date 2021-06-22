package com.example.drafttwo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.form_activity.*
import kotlinx.android.synthetic.main.home_activity.*

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)
        create.setOnClickListener{
            val title = titleField.text.toString()
            val description = descriptionField.text.toString()
            val data = hashMapOf(
               "Title" to title,
                "Description" to description
            )
            val db = FirebaseFirestore.getInstance()

            db.collection("Users").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).collection("Activities").add(data)
        }
    }

}