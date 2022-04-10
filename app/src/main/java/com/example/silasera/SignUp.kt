package com.example.silasera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.silasera.activities.MainActivity

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signIn = findViewById<TextView>(R.id.sign_in_button)
        signIn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.left_slide_out, R.anim.right_slide_in)
        }


        val backButton = findViewById<ImageView>(R.id.undoBtn)
        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_slide_out, R.anim.right_slide_in)
        }
    }
}