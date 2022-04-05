package com.example.silasera

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Firebase connection Success", Toast.LENGTH_LONG).show()



        val signUp = findViewById<TextView>(R.id.sign_up_button)
            signUp.setOnClickListener{
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out)
            }

        val forgotPassword = findViewById<TextView>(R.id.forgot_password)
        forgotPassword.setOnClickListener{
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        val continueGuest = findViewById<TextView>(R.id.guestButton)
        continueGuest.setOnClickListener{
            val intent = Intent(this, GuestMainMenu::class.java)
            startActivity(intent)
        }




    }
}